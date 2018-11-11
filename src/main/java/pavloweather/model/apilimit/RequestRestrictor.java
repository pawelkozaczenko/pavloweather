package pavloweather.model.apilimit;  

import java.util.Date;

public enum RequestRestrictor implements UsageState {
	START (true) {
		@Override
		public boolean isCurrent(){
			return conf.calculateNextVisit() == conf.NO_REQUEST
			   && conf.relativity.isPast(conf.apiMonitor.getStartTime());
		};

		@Override
		public void recordState(){
			conf.apiMonitor.recordRequestNumber(conf.FIRST_REQUEST);
		};
	},
	ALLOW_FEW (true) {
		@Override
		public boolean isCurrent(){
			return conf.calculateNextVisit() < conf.LAST_REQUEST
			&& conf.relativity.isPast(conf.apiMonitor.getStartTime());
		};

		@Override
		public void recordState(){
			conf.apiMonitor.recordRequestNumber(conf.calculateNextVisit());
		};

	},
	ALLOW_MANY (true) {
		@Override
		public boolean isCurrent(){
			return conf.calculateNextVisit() == conf.LAST_REQUEST
				&& conf.exceedTime();
		};

		private Date recalculateStartTime(){
			long interval = conf.getRelativeDuration();
			return conf.dateParser.rollBackSeconds(interval);
		}

		@Override
		public void recordState(){
			Date newStartUsage = recalculateStartTime();
			conf.apiMonitor.resetStartTreshold(newStartUsage, conf.FIRST_REQUEST);
		};

	},
	STOP (true) {
		@Override
		public boolean isCurrent(){
			return conf.calculateNextVisit() == conf.LAST_REQUEST
				&& !conf.exceedTime();
		};

		private Date moveForwardStartTime(){
			long interval = conf.getRelativeDuration();
			long remainingTimeToMax = conf.TIMEOUT_IN_SECONDS - interval;
			long limitWithExtra = conf.TIMEOUT_IN_SECONDS + remainingTimeToMax;
			return conf.dateParser.forwardSeconds(limitWithExtra);
		}

		@Override
		public void recordState(){
			Date newStartUsage = moveForwardStartTime();
			conf.apiMonitor.resetStartTreshold(newStartUsage, conf.NO_REQUEST);
		};

	},
	BLOCK (false) {
		@Override
		public boolean isCurrent(){
			return true;
		};

		@Override
		public void recordState(){
			conf.apiMonitor.recordLastTime(conf.dateParser.getCurrent());
		};

	},
	; 
	private boolean allow;

	private RequestRestrictor(boolean allow){
		this.allow = allow;
	}

	public boolean getPermission(){
		return this.allow;
	}

	private static RestrictionConfigurator conf;

	public static RequestRestrictor allowApiUsage(RestrictionConfigurator conf){
		RequestRestrictor.conf = conf;
		return determineAndRecordState();
	}
	//@TODO: add throw Exception if no state found ? 
	private static RequestRestrictor determineAndRecordState() {
		for (RequestRestrictor restrictor : RequestRestrictor.values()){
			if (restrictor.isCurrent()){
				restrictor.recordState();
				return restrictor;
			}
		}
		return RequestRestrictor.BLOCK;
	}
}
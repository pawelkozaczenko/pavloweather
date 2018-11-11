package pavloweather.model.apilimit;  

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pavloweather.model.outsource.url.ApiCity;
import pavloweather.model.date.DateParser;
import pavloweather.model.date.RelativityDefiner;
import pavloweather.model.onsite.state.ApiState;

@Service 
public class RestrictionConfigurator {

	//public final int MAX_REQUEST = 60;

	//public final int TIMEOUT_IN_SECONDS = 60; 
	
	public final int MAX_REQUEST = 10;

	public final long TIMEOUT_IN_SECONDS = 60; 
	
	public final int FIRST_REQUEST = 1;

	public final int NO_REQUEST = 0;
	
	//@TODO: change bean configuration the size of ApiCity is injected into constructor
		  //or change this to method
	public final int LAST_REQUEST = MAX_REQUEST - ApiCity.NUMBER_OF_LOCATIONS;
    
    public final ApiState apiMonitor;

    public final DateParser dateParser;

    public final RelativityDefiner relativity;

    @Autowired
    RestrictionConfigurator(ApiState apiMonitor, 
    		DateParser dateParser, RelativityDefiner relativity){
    	this.apiMonitor = apiMonitor;
    	this.dateParser = dateParser;
    	this.relativity = relativity;
    }

    public boolean exceedTime(){
			long interval = getDuration();
			return interval > TIMEOUT_IN_SECONDS;
	}
    
    public long getRelativeDuration(){
		return getDuration() % TIMEOUT_IN_SECONDS;
	}

	private long getDuration(){
		return dateParser.getSecondsRelativity(apiMonitor.getStartTime());
	}
	//@TODO: Throw exception when apiUsage is out of range
	public int calculateNextVisit(){
		int apiUsage = apiMonitor.getRequestNumber();
		if (apiUsage <= NO_REQUEST){
			return NO_REQUEST;
		}
		if (apiUsage >= MAX_REQUEST){
			return MAX_REQUEST;
		}
		else {
			return ++apiUsage;
		}
	}
}
package pavloweather.model.apilimit; 

public interface UsageState {
	
	public boolean isCurrent();

	public void recordState();
} 
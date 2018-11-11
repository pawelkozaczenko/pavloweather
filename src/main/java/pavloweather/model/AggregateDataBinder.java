package pavloweather.model;

import java.util.List;

public interface AggregateDataBinder <T extends DataItemBinder> extends DataItemBinder{

	public String getCityName();
	
	public List<T> getDailyUnits();
}
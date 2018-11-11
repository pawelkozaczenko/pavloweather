package pavloweather.model;

import java.util.Date;

public interface DataItemBinder{
	
	public Float getTemperature();
	
	public Float getMinTemperature();
	
	public Float getMaxTemperature();

	public Float getHumidity();

	public Float getPressure();
	
	public String getFormattedDate();
	
	public Date getDate();
}
package pavloweather.model.onsite.weather;

import javax.persistence.Entity;
import javax.persistence.Table;
import pavloweather.model.AggregateDataBinder;
import pavloweather.model.DataItemBinder;
import pavloweather.model.onsite.location.City;
import pavloweather.model.outsource.json.forecast.DailyUnitWrapper;

//@TODO?? Merge this class with Forcest to lower memory usage 
		//or to leave regarding SPA
@Entity
@Table(name = "FORECAST")
public class ForecastWrapper extends Forecast {
	
	public static final String CURRENT_DATE_SUFFIX = "_today";
	
	public void setCurrentWeather(City city, AggregateDataBinder<DailyUnitWrapper> apiData){
		super.setTextDate(apiData.getFormattedDate() + CURRENT_DATE_SUFFIX);
		setAllButDateAttributes(city, apiData);
	}
	
	public void setFutureWeatherAttributes(City city, DataItemBinder apiDataItem){
		super.setTextDate(apiDataItem.getFormattedDate());
		setAllButDateAttributes(city, apiDataItem);
	}
	
	private void setAllButDateAttributes(City city, DataItemBinder apiDataItem){
		super.setCity(city);
		super.setDate(apiDataItem.getDate());
		super.setHumidity(apiDataItem.getHumidity());
		super.setPressure(apiDataItem.getPressure());
		super.setTemperature(apiDataItem.getTemperature());
		super.setMinTemperature(apiDataItem.getMinTemperature());
		super.setMaxTemperature(apiDataItem.getMaxTemperature());
	}
}
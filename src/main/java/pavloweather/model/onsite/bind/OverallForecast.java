package pavloweather.model.onsite.bind;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavloweather.model.AggregateDataBinder;
import pavloweather.model.DataItemBinder;
import pavloweather.model.onsite.location.City;
import pavloweather.model.onsite.location.CityRepository;
import pavloweather.model.onsite.weather.Forecast;
import pavloweather.model.onsite.weather.ForecastRepository;
import pavloweather.model.onsite.weather.ForecastWrapper;
import pavloweather.model.outsource.url.CityUrlHandler;
//@TODO: what about SPA??
			//building dataBind UI Model, and at the same time pulling data from DB
@Service
public class OverallForecast extends DailyWeather implements AggregateDataBinder<DailyWeather> {

	@Autowired
    private CityRepository cityRepository;

    @Autowired
    private ForecastRepository forecastRepository;
	
	private String cityName;
	
	private List<DailyWeather> dailyForecasts;
	
	private static final String CURRENT_DATE_LIKE = "%" + ForecastWrapper.CURRENT_DATE_SUFFIX;
	
	@Override
	public String getCityName() {
		return cityName;
	}

	@Override
	public List<DailyWeather> getDailyUnits() {
		return dailyForecasts;
	}
	
	public void setAttributes(Forecast forecast, String cityName, List<DailyWeather> dailyForecasts) {
		super.setForecast(forecast); 
		this.cityName = cityName;
		this.dailyForecasts = dailyForecasts;
	}
	
	public AggregateDataBinder<? extends DataItemBinder> pullOnSiteData(CityUrlHandler cityUrlHandler){
		String cityName = cityUrlHandler.getCityName();
		City cityRecord = cityRepository.findFirstByName(cityName);
		Forecast currentWeatherRecord 
				= forecastRepository.findFirstByCityAndTextDateLike(cityRecord, CURRENT_DATE_LIKE);
		List<Forecast> dailyForcastRecords 
				= forecastRepository.findByCityAndTextDateNotLike(cityRecord, CURRENT_DATE_LIKE);
		List<DailyWeather> aggregateForecast = getAggregateForecast(dailyForcastRecords);
		setAttributes(currentWeatherRecord, cityName, aggregateForecast);
		return this;
	}
	
	private List<DailyWeather> getAggregateForecast(List<Forecast> dailyForcastRecords) {
		List<DailyWeather> aggregateForcast = new ArrayList<>();
		dailyForcastRecords.forEach(record -> addDailyForecast(aggregateForcast, record));
		return aggregateForcast;
	}
	
	private void addDailyForecast(List<DailyWeather> aggregateForcast, Forecast forecast) {
		DailyWeather dailyWeather = new DailyWeather();
		dailyWeather.setForecast(forecast);
		aggregateForcast.add(dailyWeather);
	}
}

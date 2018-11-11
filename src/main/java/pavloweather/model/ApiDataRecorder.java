package pavloweather.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.ImmutableList;
import pavloweather.model.onsite.location.City;
import pavloweather.model.onsite.location.CityRepository;
import pavloweather.model.onsite.weather.ForecastRepository;
import pavloweather.model.onsite.weather.ForecastWrapper;
import pavloweather.model.outsource.bind.DataFetcher;
import pavloweather.model.outsource.json.forecast.DailyUnitWrapper;
import pavloweather.model.outsource.url.CityUrlHandler;
//@TODO: what about SPA??
@Service
public class ApiDataRecorder {
	
	@Autowired
	private DataFetcher dataFetcher;
	
	@Autowired
    private CityRepository cityRepository;

    @Autowired
    private ForecastRepository forecastRepository;
	
	public void saveDataOnSite(CityUrlHandler cityUrlHandler){
		ImmutableList<CityUrlHandler> apiHandlers = cityUrlHandler.getAll();
		List<City> cityRecords = new ArrayList<City>();
		List<ForecastWrapper> weatherRecords = new ArrayList<ForecastWrapper>(); 
		populateAll(apiHandlers, cityRecords, weatherRecords);
		persistOnDatabase(cityRecords, weatherRecords);
	}
	
	private void populateAll(ImmutableList<CityUrlHandler> apiHandlers, 
				List<City> cityRecords, List<ForecastWrapper> weatherRecords) {
		apiHandlers.forEach(handler -> 
						populateSpecificLocation(handler, cityRecords, weatherRecords));		 	
	}
	
	private void populateSpecificLocation(CityUrlHandler specificHandler, 
				List<City> cityRecords, List<ForecastWrapper> weatherRecords) {
			AggregateDataBinder<DailyUnitWrapper> apiData = dataFetcher.pull(specificHandler);
			City city = createCityRecord(apiData);	
			cityRecords.add(city);
			ForecastWrapper currentWeather = createCurrentWeatherRecord(city, apiData);
			weatherRecords.add(currentWeather);
			apiData.getDailyUnits().forEach(dailyForecast -> 
							populateDailyForecast(dailyForecast, weatherRecords, city));
	}
	
	private void populateDailyForecast(DailyUnitWrapper dailyData, List<ForecastWrapper> weatherRecords,
			City city) {
		ForecastWrapper futureWeather = createForecastWeatherRecord(city, dailyData);
		weatherRecords.add(futureWeather);
	}
	
	private City createCityRecord(AggregateDataBinder<DailyUnitWrapper> apiData) {
		City city = new City();
		city.setName(apiData.getCityName());
		return city;
	}
	
	private ForecastWrapper createCurrentWeatherRecord(City city, AggregateDataBinder<DailyUnitWrapper> apiData) {
		ForecastWrapper weather = new ForecastWrapper();
		weather.setCurrentWeather(city, apiData);
		return weather;
	}
	
	private ForecastWrapper createForecastWeatherRecord(City city, DailyUnitWrapper dailyData) {
		ForecastWrapper weather = new ForecastWrapper();
		weather.setFutureWeatherAttributes(city, dailyData);
		return weather;
	}
	
	private void persistOnDatabase(List<City> cityRecords, List<ForecastWrapper> weatherRecords) {
		cityRecords.forEach(el -> cityRepository.save(el));
		weatherRecords.forEach(el -> forecastRepository.save(el));
	} 
}
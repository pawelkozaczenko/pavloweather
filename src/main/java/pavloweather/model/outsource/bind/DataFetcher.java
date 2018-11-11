package pavloweather.model.outsource.bind;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import pavloweather.model.outsource.json.daily.CurrentWeather;
import pavloweather.model.outsource.json.forecast.AggregateForecast;
import pavloweather.model.outsource.url.CityUrlHandler;

@Service
public class DataFetcher{

    @Autowired
    private AggregateModel aggregateModel;

    @Autowired
    private RestTemplate restTemplate;

	public AggregateModel pull(CityUrlHandler cityUrlHandler){
        aggregateModel.setCityName(cityUrlHandler.getFormatterName());
        aggregateModel.setCurrentWeather(getCurrentWeather(restTemplate, cityUrlHandler));
        aggregateModel.setAggregateForecast(getAggregateForecast(restTemplate, cityUrlHandler));
        return aggregateModel;
	}

    //??TODO: make paremater class
    private CurrentWeather getCurrentWeather(RestTemplate restTemplate, CityUrlHandler cityUrlHandler){
        return restTemplate
                .getForObject(
                    cityUrlHandler.getDailyWeatherLink(), 
                    CurrentWeather.class
                );
    }

    private AggregateForecast getAggregateForecast(RestTemplate restTemplate, CityUrlHandler cityUrlHandler){
        return restTemplate
                .getForObject(
                    cityUrlHandler.getForecastLink(), 
                    AggregateForecast.class
                );
    }
}

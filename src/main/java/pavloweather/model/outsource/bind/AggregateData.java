package pavloweather.model.outsource.bind;

import pavloweather.model.outsource.json.daily.CurrentWeather;
import pavloweather.model.outsource.json.forecast.AggregateForecast;

public abstract class AggregateData{

    private String cityName;

    private CurrentWeather currentWeather;

    private AggregateForecast aggregateForecast;

    protected void setCityName(String cityName){
        this.cityName = cityName;
    }

    protected void setCurrentWeather(CurrentWeather currentWeather){
        this.currentWeather = currentWeather;
    }

    protected void setAggregateForecast(AggregateForecast aggregateForecast){
        this.aggregateForecast = aggregateForecast;
    }

    protected String getCityName(){
        return cityName;
    }

    protected CurrentWeather getCurrentWeather(){
        return currentWeather;
    }

    protected AggregateForecast getAggregateForecast(){
        return aggregateForecast;
    }  
}
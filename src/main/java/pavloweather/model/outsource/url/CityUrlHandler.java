package pavloweather.model.outsource.url;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import org.springframework.stereotype.Service;

@Service
public class CityUrlHandler{

    private final String API_KEY = "f6684645d53947381e1544e24a1969dd";

    private final String URL_PATTERN = "http://api.openweathermap.org/data/2.5/%s?q=%s,%s&appid=%s";

    private ApiCity apiCity;
    
    private enum ApiDataVaration {
        
        DAILY_WEATHER("weather"),

        FORECAST("forecast");
    
        private final String type;

        ApiDataVaration(String type){
            this.type = type;
        }

        protected String getType(){
            return type;
        }
    }
    
    //@TODO: change this property by configuring bean and inject in constructor
    CityUrlHandler(){
    	apiCity = ApiCity.getDefault();
    }
    
    CityUrlHandler(ApiCity apiCity){
    	this.apiCity = apiCity;
    }
    
    public void setCityByUrl(String cityUrlVal){
    	this.apiCity = ApiCity.getByUrl(cityUrlVal);
    }
    
    public String getCityName(){
    	return apiCity.getName(); 
    }
    
    public ImmutableList<CityUrlHandler> getAll(){
    	Builder<CityUrlHandler> urlsBuilder = new ImmutableList.Builder<>();
        ImmutableList<ApiCity> locations = ApiCity.getWholeSet();
        locations.forEach(loc -> {
        	CityUrlHandler handler = new CityUrlHandler(loc);
        	urlsBuilder.add(handler);
        });
        return urlsBuilder.build();
    }

    public String getFormatterName(){
        return apiCity.getName();
    }
    
    public String getDailyWeatherLink(){
        return getApiLink(ApiDataVaration.DAILY_WEATHER);
    }

    public String getForecastLink(){
        return getApiLink(ApiDataVaration.FORECAST);
    }

    private String getApiLink(ApiDataVaration type){
        return String.format(URL_PATTERN, type.getType(), 
        		apiCity.getUrlVal(), apiCity.getCountryCode(), API_KEY);
    }
}
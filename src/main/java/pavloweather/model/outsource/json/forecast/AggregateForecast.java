package pavloweather.model.outsource.json.forecast;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateForecast {
    
    private City city;
    
    @JsonProperty("cnt")
    private Long count;
    
    @JsonProperty("list")
    private List<DailyUnitWrapper> dailyUnits;
    
    public City getCity(){
        return city;
    }

    public Long getCount(){
        return count;
    }

    public List<DailyUnitWrapper> getDailyUnits(){
        return dailyUnits;
    }

    public void setCity(City city){
        this.city = city;
    }

    public void setCount(Long count){
        this.count = count;
    }

    public void setDailyUnits(List<DailyUnitWrapper> dailyUnits){
        this.dailyUnits = dailyUnits;
    }
}
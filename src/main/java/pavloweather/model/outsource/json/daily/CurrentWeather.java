package pavloweather.model.outsource.json.daily;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather{
    
    @JsonProperty("dt")
    private Date rawDate;
    
    private Long id;
    
    private String name;
    
    private Main main;

    public Date getRawDate() {
        return rawDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }

    public void setRawDate(Date rawDate) {
        this.rawDate = rawDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setMain(Main main) {
        this.main = main;
    }
}
package pavloweather.model.outsource.json.forecast;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pavloweather.model.outsource.json.daily.Main;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyUnit {

    @JsonProperty("dt")
    private Date date;
    
    @JsonProperty("dt_txt") 
    private String dateText;

    private Main main;
    
    public Date getRawDate() {
        return date;
    }

    public void setRawDate(Date date){
        this.date = date;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText){
        this.dateText = dateText;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }
}
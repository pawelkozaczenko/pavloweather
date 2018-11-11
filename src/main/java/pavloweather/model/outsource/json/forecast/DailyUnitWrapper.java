package pavloweather.model.outsource.json.forecast;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pavloweather.model.DataItemBinder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyUnitWrapper extends DailyUnit implements DataItemBinder {

    @Override
    public Float getTemperature(){
        return super.getMain().getTemperature();
    }
    
    @Override
    public Float getMinTemperature(){
        return super.getMain().getMinTemperature();
    }
    
    @Override
    public Float getMaxTemperature(){
        return super.getMain().getMaxTemperature();
    }

    @Override
    public Float getHumidity(){
        return super.getMain().getHumidity();
    }

    @Override
    public Float getPressure(){
        return super.getMain().getPressure();
    }
    
    @Override
    public Date getDate(){
        return super.getRawDate();
    }

    @Override
    public String getFormattedDate(){
        return super.getDateText();
    }
}
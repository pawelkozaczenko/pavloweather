package pavloweather.model.outsource.bind;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavloweather.model.AggregateDataBinder;
import pavloweather.model.outsource.json.forecast.DailyUnitWrapper;
import pavloweather.model.date.DateParser;

@Service
public class AggregateModel extends AggregateData implements AggregateDataBinder<DailyUnitWrapper>{

	@Autowired 
	private DateParser dateParser;

	public String getCityName(){
        return super.getCityName();
    }

    @Override
	public Float getTemperature(){
		return super.getCurrentWeather().getMain().getTemperature();
	}
    
    @Override
	public Float getMinTemperature(){
		return super.getCurrentWeather().getMain().getMinTemperature();
	}
    
    @Override
	public Float getMaxTemperature(){
		return super.getCurrentWeather().getMain().getMaxTemperature();
	}

	@Override
	public Float getHumidity(){
		return super.getCurrentWeather().getMain().getHumidity();
	}

	@Override
	public Float getPressure(){
		return super.getCurrentWeather().getMain().getPressure();
	}
	
	@Override
	public String getFormattedDate(){
		Date rawDate = super.getCurrentWeather().getRawDate();
		return dateParser.format(rawDate);
	}
	
	@Override
	public Date getDate(){
		return super.getCurrentWeather().getRawDate();
	}
	
	@Override
    public List<DailyUnitWrapper> getDailyUnits(){
    	return super.getAggregateForecast().getDailyUnits();
    }
}
package pavloweather.model.onsite.bind;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import pavloweather.model.DataItemBinder;
import pavloweather.model.date.DateParser;
import pavloweather.model.onsite.weather.Forecast;
//@TODO?? Merge this class with Forcest to lower memory usage 
			//or to leave regarding SPA, if this is SPA
public class DailyWeather implements DataItemBinder{
	//@Autowired
	//private DateParser dateParser;
	
    private Forecast forecast;
    
    public void setForecast(Forecast forecast) {
    	this.forecast = forecast;
    }

	@Override
	public Float getTemperature() {
		return forecast.getTemperature();
	}

	@Override
	public Float getMinTemperature() {
		return forecast.getMinTemperature();
	}

	@Override
	public Float getMaxTemperature() {
		return forecast.getMaxTemperature();
	}

	@Override
	public Float getHumidity() {
		return forecast.getHumidity();
	}

	@Override
	public Float getPressure() {
		return forecast.getPressure();
	}

	@Override
	public Date getDate() {
		return forecast.getDate();
	}
	
	@Override
	public String getFormattedDate() {
		return forecast.getTextDate();
	}
}

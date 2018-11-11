package pavloweather.model.onsite.weather;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import pavloweather.model.onsite.location.City;

@Entity
public class Forecast {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
    private Date date;

    private String textDate;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

    private Float temperature;

    private Float minTemperature;

    private Float maxTemperature;
    
    private Float pressure;
    
    private Float humidity;

    public Date getDate(){
        return date;
    }

    public City getCity(){
        return city;
    }

    public String getTextDate(){
        return textDate;
    }

    public Float getTemperature() {
        return temperature;
    }

    public Float getMinTemperature() {
        return minTemperature;
    }

    public Float getMaxTemperature() {
        return maxTemperature;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getHumidity() {
        return humidity;
    }
    
    public void setDate(Date date){
        this.date = date;
    }

    public void setCity(City city){
        this.city = city;
    }

    public void setTextDate(String textDate){
        this.textDate = textDate;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public void setMinTemperature(Float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(Float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    } 
}
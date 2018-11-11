package pavloweather.model.outsource.json.daily;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    @JsonProperty("temp")
    private Float temperature;

    @JsonProperty("temp_min")
    private Float minTemperature;

    @JsonProperty("temp_max")
    private Float maxTemperature;
    
    private Float pressure;
    
    private Float humidity;

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
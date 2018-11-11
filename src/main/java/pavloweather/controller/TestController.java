package pavloweather.controller;

import java.util.Date;
import java.util.List;
import java.lang.Iterable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pavloweather.model.onsite.location.CityRepository;
import pavloweather.model.onsite.state.ApiState;
import pavloweather.model.onsite.state.ApiUsage;
import pavloweather.model.onsite.state.ApiUsageRepository;
import pavloweather.model.onsite.location.City;
import pavloweather.model.onsite.weather.Forecast;
import pavloweather.model.onsite.weather.ForecastRepository;
import pavloweather.model.onsite.weather.ForecastWrapper;
import pavloweather.model.outsource.url.CityUrlHandler;
import pavloweather.model.date.DateParser;
import pavloweather.model.ApiDataRecorder;
import pavloweather.model.apilimit.RequestRestrictor;
import pavloweather.model.apilimit.RestrictionConfigurator;

//@TODO: replace test controller by unit tests 
			//(Which should encompass much wider scope)
@Controller
@RequestMapping("/demo")
public class TestController {

    @Autowired
    private DateParser dateParser;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ApiUsageRepository apiUsageRepository;
    
    @Autowired
    private ApiState apiState;

    @Autowired
    private ApiState apiMonitor;

    @Autowired
    private ForecastRepository dailyUnitRepository;

    @Autowired
    private RestrictionConfigurator restrictionConfigurator;

    @RequestMapping("/cities")
    public @ResponseBody Iterable<City> showDatabaseCities(){
    	return cityRepository.findAll();
    }

    @RequestMapping("/apistate")
    public @ResponseBody Iterable<ApiUsage> showApiStateDbRecorder(){
    	return apiUsageRepository.findAll();
    }

    @RequestMapping("/weather")
    public @ResponseBody Iterable<Forecast> showDailyWeatherDbRecords(){
    	return dailyUnitRepository.findAll();
    }

    @RequestMapping("/apistate/formatted")
    public @ResponseBody String checkstate(){
    	Date start = apiState.getStartTime();
    	Date last = apiState.getLastTime();
    	int reqNumber = apiState.getRequestNumber();
        return "start is : " + dateParser.format(start)
        		+ " end is : " + dateParser.format(last)
        		+ " count: " + reqNumber + " nex visit is " + restrictionConfigurator.calculateNextVisit()
        		+ " duration : " + dateParser.getSecondsRelativity(apiMonitor.getStartTime())
        		+ " duration (module) : " + dateParser.getSecondsRelativity(apiMonitor.getStartTime()) % 60;
    }

    @RequestMapping("/apistate/check")
    public @ResponseBody String check(){
    	RequestRestrictor res = RequestRestrictor.allowApiUsage(restrictionConfigurator);
        return "apiUsageState is: " + res.getPermission();
    }
}
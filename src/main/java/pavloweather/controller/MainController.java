package pavloweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import pavloweather.model.outsource.url.CityUrlHandler;
import pavloweather.model.AggregateDataBinder;
import pavloweather.model.ModelDataResolver;
import pavloweather.model.DataItemBinder;


@Controller
public class MainController {
	@Autowired
	private ModelDataResolver dataFetcher;
	
	@Autowired
	private CityUrlHandler cityUrlHandler;

    @RequestMapping("/city")
    public String showLocationWeather(
            @RequestParam(value="city_name", required=false, defaultValue="london") String cityUrlVal, 
            @RequestParam(value="country_code", required=false, defaultValue="GB") String countryCode,
             Model model){	
    	//cityUrlHandler.setCityByUrl(cityUrlVal);
    	//AggregateDataBinder<? extends DataItemBinder> apiData = dataFetcher.get(cityUrlHandler);
    	//model.addAttribute("apiData", apiData);
    	return "template";
    	//return "index";
        //uncomment this later and add front end in angular
    }
}
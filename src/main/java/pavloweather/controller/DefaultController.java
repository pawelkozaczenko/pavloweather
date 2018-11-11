package pavloweather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping(value={"/", "/home", "/welcome", "/index"})
    public String greeting() {
        return "index";
    }
}
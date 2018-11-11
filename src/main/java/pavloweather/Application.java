package pavloweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"pavloweather.models", "pavloweather.controller"})
@SpringBootApplication 
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}
}
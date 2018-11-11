package pavloweather.model.onsite.weather;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pavloweather.model.onsite.location.City;

public interface ForecastWrapperRepository extends CrudRepository<ForecastWrapper, Integer> {
	
	List<ForecastWrapper> findByCityAndTextDateNotLike(City city, String textDate);

	Forecast findFirstByCityAndTextDateLike(City city, String textDate);
}
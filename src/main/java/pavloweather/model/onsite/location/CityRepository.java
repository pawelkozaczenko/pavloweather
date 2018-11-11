package pavloweather.model.onsite.location;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {

	City findFirstByName(String name);
}
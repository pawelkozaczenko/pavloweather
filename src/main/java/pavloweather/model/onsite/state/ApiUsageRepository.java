package pavloweather.model.onsite.state;

import org.springframework.data.repository.CrudRepository;

public interface ApiUsageRepository extends CrudRepository<ApiUsage, Integer> {
	
	ApiUsage findFirstByOrderById();
}
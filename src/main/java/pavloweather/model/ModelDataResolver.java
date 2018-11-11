package pavloweather.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavloweather.model.onsite.bind.OverallForecast;
import pavloweather.model.outsource.bind.DataFetcher;
import pavloweather.model.outsource.url.CityUrlHandler;

@Service
public class ModelDataResolver {

	@Autowired
	private ApiDataRecorder apiDataRecorder;
	
	@Autowired
	private DataFetcher dataFetcher;
	
	@Autowired
	private OverallForecast overallForecast;	
	//@TODO: switch default case throw Exception?
	public AggregateDataBinder<? extends DataItemBinder> get(CityUrlHandler cityUrlHandler) {
		apiDataRecorder.saveDataOnSite(cityUrlHandler);
		return overallForecast.pullOnSiteData(cityUrlHandler);
		//return dataFetcher.pull(cityUrlHandler);
		//RequestRestrictor state = RequestRestrictor.allowApiUsage(conf);
		/*switch(state){
			case START:
			case ALLOW_FEW:
			case ALLOW_MANY:
				apiData = dataFetcher.pull(cityUrlHandler);
				saveData(cityUrlHandler);
				break;
			case STOP:
				//getdata
				apiData = dataFetcher.pull(cityUrlHandler);
			default:
				//get data from database
				apiData = dataFetcher.pull(cityUrlHandler);
				
		}
		return apiData;*/
	}
}
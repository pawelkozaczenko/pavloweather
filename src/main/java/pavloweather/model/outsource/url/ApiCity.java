package pavloweather.model.outsource.url;

import com.google.common.collect.ImmutableList;

public class ApiCity{
	   
    private final static int DEFAULT = 0;
    
    public static final ImmutableList<ApiCity> API_CITIES = ImmutableList.of(
       new ApiCity("london", "London", "GB"),
       new ApiCity("newyork", "New York", "US"),
       new ApiCity("washington", "Washington" , "US")
    );
    
    public static final int NUMBER_OF_LOCATIONS = ApiCity.getWholeSet().size();

    private final String urlVal;
    
    private final String name;

    private final String countryCode;
	
	private ApiCity(String urlVal, String name, String countryCode){
		this.urlVal = urlVal;
		this.name = name;
        this.countryCode = countryCode;
	}
	
	public String getUrlVal() {
		return urlVal;
	} 
	
	public String getName() {
		return name;
	} 
	
	public String getCountryCode() {
		return countryCode;
	} 
	//@TODO: throw Exception if url not found 
	public static ApiCity getByUrl(String cityUrlVal) {
		return API_CITIES.stream()
				.filter(el -> el.urlVal.equals(cityUrlVal))
				.findAny()
				.orElse(getDefault());	
	}
	
	public static ApiCity getDefault(){
    	return API_CITIES.get(DEFAULT);
    }
	
    public static ImmutableList<ApiCity> getWholeSet(){
    	return API_CITIES;
    }
}
package pavloweather.model.onsite.state;

import java.util.Date;
import java.lang.Integer;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pavloweather.model.date.DateParser;
import pavloweather.model.onsite.state.ApiUsage;
import pavloweather.model.onsite.state.ApiUsageRepository;

@Service
public class ApiState{

	@Autowired
    private ApiUsageRepository apiUsageRepository;
	
	@Autowired
    private DateParser dateParser;

    public Date getStartTime(){
    	return getMonitor().getFirst();
    };

    private void setStartTime(Date date){
    	getMonitor().setFirst(date);
    };

    public Date getLastTime(){
        return getMonitor().getLast();
    };

    private void setLastTime(Date date){
    	getMonitor().setLast(date);
    };

    public int getRequestNumber(){
        Integer visitNumber = getMonitor().getNumber();
        return visitNumber.intValue();
    }

    private void setRequestNumber(int useNumber){
        Integer visitNumber = Integer.valueOf(useNumber);
        getMonitor().setNumber(visitNumber);
    }

    public void recordRequestNumber(int useNumber){
        setRequestNumber(useNumber);
        setLastTime(dateParser.getCurrent());
        saveState();
    }

    public void recordLastTime(Date date){
        setLastTime(date);
        saveState();
    }

    public void resetStartTreshold(Date date, int useNumber){
    	setStartTime(date);
        setRequestNumber(useNumber);
        saveState();
    }
    
	private ApiUsage getMonitor(){
		return apiUsageRepository.findFirstByOrderById();
	}
	
    private void saveState(){
        ApiUsage monitor = getMonitor();
        apiUsageRepository.save(monitor);
    }
}
package pavloweather.model.date;

import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RelativityDefiner {

    public enum Perspective { PAST, PRESENT, FUTURE }; 

    @Autowired
    private DateParser dateParser;

    public boolean isFuture(Date date){
        Perspective relativity = approximate(date);
        return relativity.equals(Perspective.FUTURE);
    }

    public boolean isPast(Date date){
        Perspective relativity = approximate(date);
        return relativity.equals(Perspective.PAST);
    }

    public Perspective approximate(Date date){
        long duration = dateParser.getSecondsRelativity(date);
        if (duration > 0){
            return Perspective.PAST;
        }
        if (duration < 0){
            return Perspective.FUTURE;
        }
        return Perspective.PRESENT;
    }
}
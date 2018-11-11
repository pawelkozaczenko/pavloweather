package pavloweather.model.date;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Service;

@Service
public class DateParser {

    private final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";

    private final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    private final int MILISECONDS_IN_SECOND = 1000;
    
    public String format(long milisecunds){
        return SIMPLE_DATE_FORMAT.format(convertToDate(milisecunds));
    }

    public String format(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public String formatCurrent(){
        return SIMPLE_DATE_FORMAT.format(getCurrent());
    }
    public long getCurrentMilisecods(){
        return getCurrent().getTime();
    }
    
    private Date convertToDate(long miliseconds) {
    	return new Date(miliseconds);
    }

    public Date getCurrent(){
        return new Date();
    }

    public Date rollBackSeconds(long seconds){
        long now = convertMilisecondsToSeconds(getCurrentMilisecods());
        long past = now - seconds;
        return convertToDate(convertSecondsToMiliseconds(past));
    }

    public Date forwardSeconds(long seconds){
        long now = convertMilisecondsToSeconds(getCurrentMilisecods());
        long future = now + seconds;
        return convertToDate(convertSecondsToMiliseconds(future));
    }

    public long getSecondsRelativity(Date date){
        return getSecondsDelta(getCurrent(), date);
    }

    public long getSecondsDelta(Date start, Date stop){
        long delta = getMilisecondsDiff(start, stop);
        return convertMilisecondsToSeconds(delta);
    }

    public long getMilisecondsDiff(Date start, Date stop){
        return start.getTime() - stop.getTime();
    }

    public long convertMilisecondsToSeconds(long milisecuds){
        return milisecuds / MILISECONDS_IN_SECOND ;
    }
    
    public long convertSecondsToMiliseconds(long secunds){
        return secunds * MILISECONDS_IN_SECOND ;
    }
}
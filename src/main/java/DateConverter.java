import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateConverter {
    public static LocalDate ConvertDate(JSONObject dayData){
        DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateData = dayData.get("Date").toString();
        dateData = dateData.substring(0, 10);
        LocalDate date = LocalDate.parse(dateData, formatOfDate);
        return date;
    }
}
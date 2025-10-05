package Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getTimeDate {
    public String dateTime;
    public String getDatetime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        dateTime = LocalDateTime.now().format(formatter);
        return dateTime;
    }
}

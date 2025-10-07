package Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class getTimeDate {
    public static String getDatetime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return LocalDateTime.now().format(formatter);
    }
}
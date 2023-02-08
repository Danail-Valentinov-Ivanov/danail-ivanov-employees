package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateParser {
    public static LocalDate parseDate(String date){
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException exception){
            System.out.println("Invalid format exception of date field: " + exception.getMessage());
        }
        return parsedDate;
    }
}

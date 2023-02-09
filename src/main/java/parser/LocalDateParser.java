package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class LocalDateParser {

    private final static Map<String, String> dateRegexps = new HashMap<>();

    static {
        dateRegexps.put("^\\d{8}$", "yyyyMMdd");
        dateRegexps.put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        dateRegexps.put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        dateRegexps.put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        dateRegexps.put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        dateRegexps.put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        dateRegexps.put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
    }

    public static LocalDate parseDate(String date){
        DateTimeFormatter dateFormatter;
        String regexp = getRegexp(date);
        LocalDate parsedDate = null;
        try {
            dateFormatter = DateTimeFormatter.ofPattern(regexp);
            parsedDate = LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException exception){
            System.out.println("Invalid format exception of date field: " + exception.getMessage());
        }
        return parsedDate;
    }

    public static String getRegexp(String dateString) {
        String pattern = null;
        for (String regex : dateRegexps.keySet()) {
            if (dateString.matches(regex)) {
                pattern = dateRegexps.get(regex);
            }
        }
        return pattern;
    }
}

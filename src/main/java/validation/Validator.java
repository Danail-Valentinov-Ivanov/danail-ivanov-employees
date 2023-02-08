package validation;

import parser.LocalDateParser;

import java.time.LocalDate;
import java.time.Period;

public class Validator {
    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidPeriod(LocalDate dateFrom, LocalDate dateTo) {

        return dateFrom.isBefore(dateTo);
    }

    public static boolean isValidDateFrom(String dateFrom){
        return LocalDateParser.parseDate(dateFrom) != null;
    }

    public static boolean isValidDateTo(String dateTo){
        return dateTo.equals("NULL") || LocalDateParser.parseDate(dateTo) != null;
    }

    public static LocalDate getTodayDateTo(String dateTo){
        LocalDate localDateTo = null;
        if (dateTo.equals("NULL")){
            localDateTo = LocalDate.now();
        } else if(LocalDateParser.parseDate(dateTo) != null){
            localDateTo = LocalDateParser.parseDate(dateTo);
        }
        return localDateTo;
    }

    public static boolean isCommonPeriod (LocalDate employee1DateFrom, LocalDate employee1DateTo
            , LocalDate employee2DateFrom, LocalDate employee2DateTo){

        Period period = Period.between(employee1DateFrom, employee2DateTo);



        return (employee1DateFrom.isAfter(employee2DateFrom) && employee1DateFrom.isBefore(employee2DateTo));
    }

//    public static LocalDate parseDate(String dateTo){
//        LocalDate parsedDate;
//        try {
//            parsedDate = LocalDate.parse(dateTo);
//        } catch (DateTimeParseException exception){
//            return null;
//        }
//        return parsedDate;
//    }
}

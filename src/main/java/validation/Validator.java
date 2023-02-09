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

    public static Period checkCommonPeriod (LocalDate employee2DateFrom
            , LocalDate employee1DateTo, LocalDate employee2DateTo){
        LocalDate startCommonPeriod;
        LocalDate endCommonPeriod;
        Period commonPeriod = null;
        if (!employee1DateTo.isBefore(employee2DateFrom)){
            startCommonPeriod = employee2DateFrom;
            if (employee1DateTo.isBefore(employee2DateTo)){
                endCommonPeriod = employee1DateTo;
            } else {
                endCommonPeriod = employee2DateTo;
            }
            commonPeriod = Period.between(startCommonPeriod, endCommonPeriod);
        }

        return commonPeriod;
    }

}

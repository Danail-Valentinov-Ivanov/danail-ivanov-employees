package util;

import model.EmployeeData;
import validation.Validator;

import java.time.LocalDate;
import java.util.List;

import static java.util.concurrent.TimeUnit.DAYS;

public class FindPair {
    public static String findLongestPeriod(List<EmployeeData>employeeDataList){
        String result = "";
        for (int i = 0; i < employeeDataList.size() - 1; i++) {
            EmployeeData employeeDataFirst = employeeDataList.get(i);
            EmployeeData employeeDataSecond = employeeDataList.get(i + 1);
            LocalDate employee1DateFrom = employeeDataFirst.getLocalDateFrom();
            LocalDate employee1DateTo = employeeDataFirst.getLocalDateTo();
            LocalDate employee2DateFrom = employeeDataSecond.getLocalDateFrom();
            LocalDate employee2DateTo = employeeDataSecond.getLocalDateTo();
            boolean isCommonProject = employeeDataFirst.getProjectId() == employeeDataSecond.getProjectId();
            boolean isCommonPeriod = Validator.isCommonPeriod(employee1DateFrom, employee1DateTo
                    , employee2DateFrom, employee2DateTo);
            if (isCommonProject && isCommonPeriod){
                long maxDays = 0;
                long currentDays = DAYS.toChronoUnit().between(employee1DateFrom, employee2DateTo);
//                to consider if there are equal days!!!
                if (currentDays >= maxDays){
                    maxDays = currentDays;

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(employeeDataFirst.getEmployeeId()).append(", ")
                            .append(employeeDataSecond.getEmployeeId()).append(", ")
                            .append(employeeDataSecond.getProjectId()).append(", ")
                            .append(maxDays);
                    result = stringBuilder.toString();
                }
            }
        }
        return result;
    }
}

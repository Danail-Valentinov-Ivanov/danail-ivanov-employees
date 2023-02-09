package util;

import model.EmployeeData;
import validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindPair {

    public static String findLongestPeriod(List<EmployeeData>employeeDataList){
        String result = "";
        Map<Integer, List<EmployeeData>> projectsMap = employeeDataList
                .stream()
                .collect(Collectors.groupingBy(EmployeeData::getProjectId));

        List<EmployeeData> newList = new ArrayList<>();
        for (Map.Entry<Integer, List<EmployeeData>> list : projectsMap.entrySet()) {
            List<EmployeeData> currentList = list.getValue()
                    .stream()
                    .sorted(Comparator.comparing(EmployeeData::getLocalDateFrom))
                    .collect(Collectors.toList());
            for (EmployeeData employeeData:currentList) {
                newList.add(employeeData);
            }
        }

        for (int i = 0; i < newList.size(); i++) {
            EmployeeData employeeDataFirst = newList.get(i);
            for (int j = 0; j < newList.size(); j++) {
                if (i == j){
                    continue;
                }
                EmployeeData employeeDataSecond = newList.get(j);
                LocalDate employee2DateFrom = employeeDataSecond.getLocalDateFrom();
                LocalDate employee1DateTo = employeeDataFirst.getLocalDateTo();
                LocalDate employee2DateTo = employeeDataSecond.getLocalDateTo();
                boolean isCommonProject = (employeeDataFirst.getProjectId() == employeeDataSecond.getProjectId());
                Period commonPeriod = Validator.checkCommonPeriod(employee2DateFrom, employee1DateTo
                        , employee2DateTo);
                if (isCommonProject && (commonPeriod != null)){
                    long maxDays = 0;
                    long currentDays = commonPeriod.getDays();
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
        }
        return result;
    }
}

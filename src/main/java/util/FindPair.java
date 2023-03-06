package util;

import model.EmployeeData;
import model.Pair;
import validation.Validator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FindPair {

    public static List<Pair> findLongestPeriod(List<EmployeeData>employeeDataList){
        Pair pair;
        Map<Long, List<Pair>>resultMap = new HashMap<>();

        List<EmployeeData> newList = employeeDataList.stream()
                .sorted(Comparator.comparing(EmployeeData::getLocalDateFrom))
                .collect(Collectors.toList());
        long maxDays = 0;
        for (int i = 0; i < newList.size() - 1; i++) {
            EmployeeData employeeDataFirst = newList.get(i);
            for (int j = i + 1; j < newList.size(); j++) {
                EmployeeData employeeDataSecond = newList.get(j);

                LocalDate employee2DateFrom = employeeDataSecond.getLocalDateFrom();
                LocalDate employee1DateTo = employeeDataFirst.getLocalDateTo();
                LocalDate employee2DateTo = employeeDataSecond.getLocalDateTo();
                boolean isCommonProject = (employeeDataFirst.getProjectId() == employeeDataSecond.getProjectId());
                long currentDays = Validator.checkCommonPeriod(employee2DateFrom, employee1DateTo
                        , employee2DateTo);
                if (isCommonProject && (currentDays != -1)){
                    if (currentDays >= maxDays){
                        pair = new Pair(employeeDataFirst.getEmployeeId(), employeeDataSecond.getEmployeeId()
                        , employeeDataSecond.getProjectId(), currentDays);

                        if (resultMap.containsKey(currentDays)){
                            resultMap.get(currentDays).add(pair);
                        } else {
                            List<Pair>currentList = new ArrayList<>();
                            currentList.add(pair);
                            resultMap.put(currentDays, currentList);
                        }
                        maxDays = currentDays;
                    }
                }
            }
        }
        return resultMap.get(maxDays);
    }
}

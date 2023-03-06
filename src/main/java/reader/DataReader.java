package reader;

import parser.LocalDateParser;
import model.EmployeeData;
import validation.Validator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public List<EmployeeData> readDataFile(String path) throws IOException {
        List<EmployeeData> employeeDataList = new ArrayList<>();
//        read data from file
        FileInputStream fileInputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] splitArray = line.split(", ");
            int employeeId = Integer.parseInt(splitArray[0]);
            int projectId = Integer.parseInt(splitArray[1]);
            String dateFrom = splitArray[2];
            String dateTo = splitArray[3];
            boolean isValidEmployeeId = Validator.isValidId(employeeId);
            boolean isValidProjectId = Validator.isValidId(projectId);
            boolean isValidDateFrom = Validator.isValidDateFrom(dateFrom);
            boolean isValidDateTo = Validator.isValidDateTo(dateTo);

            if (isValidEmployeeId && isValidProjectId && isValidDateFrom && isValidDateTo) {
                LocalDate localDateFrom = LocalDateParser.parseDate(dateFrom);
                LocalDate localDateTo = Validator.getTodayDateTo(dateTo);
                boolean isValidPeriod = Validator.isValidPeriod(localDateFrom, localDateTo);
                if (isValidPeriod) {
                    EmployeeData employeeData = new EmployeeData(employeeId, projectId
                            , localDateFrom, localDateTo);
                    employeeDataList.add(employeeData);
                } else {
                    throw new IllegalArgumentException("Illegal input period in line: " + line);
                }
            } else {
                throw new IllegalArgumentException("Illegal input line: " + line);
            }

            line = bufferedReader.readLine();
        }
        bufferedReader.close();

        return employeeDataList;
    }
}

package reader;

import parser.LocalDateParser;
import model.EmployeeData;
import validation.Validator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public List<EmployeeData> readDataFile(String path){
        List<EmployeeData> employeeDataList = new ArrayList<>();
//        read data from file
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null){
//                System.out.println(line);
                String[] splitArray = line.split(", ");
                int employeeId = Integer.parseInt(splitArray[0]);
                int projectId = Integer.parseInt(splitArray[1]);
                String dateFrom = splitArray[2];
                String dateTo = splitArray[3];
                boolean isValidEmployeeId = Validator.isValidId(employeeId);
                boolean isValidProjectId = Validator.isValidId(projectId);
                boolean isValidDateFrom = Validator.isValidDateFrom(dateFrom);
                boolean isValidDateTo = Validator.isValidDateTo(dateTo);

                if (isValidEmployeeId && isValidProjectId && isValidDateFrom && isValidDateTo){
                    LocalDate localDateFrom = LocalDateParser.parseDate(dateFrom);
                    LocalDate localDateTo = Validator.getTodayDateTo(dateTo);
                    EmployeeData employeeData = new EmployeeData(employeeId, projectId
                            , localDateFrom, localDateTo);
                    employeeDataList.add(employeeData);
                }

                line = bufferedReader.readLine();

            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }

        return employeeDataList;
    }
}

import model.Pair;
import reader.DataReader;
import model.EmployeeData;
import util.FindPair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Run {

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        List<EmployeeData> employeeDataList = new ArrayList<>();
        String path = "src/main/resources/csv.txt";
//        test paths: invalidDataPath, invalidPeriodPath!
        String invalidData = "src/main/resources/invalidDataCsv.txt";
        String invalidPeriod = "src/main/resources/invalidPeriodCsv.txt";
        try {
            employeeDataList = dataReader.readDataFile(invalidPeriod);
        } catch (IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage());
        } catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
        List<Pair> resultList = FindPair.findLongestPeriod(employeeDataList);
        for (Pair line:resultList) {
            System.out.printf("%d, %d, %d, %d%n", line.getEmployeeId1(), line.getEmployeeId2()
                    , line.getProjectId(), line.getCommonDays());
        }
    }
}

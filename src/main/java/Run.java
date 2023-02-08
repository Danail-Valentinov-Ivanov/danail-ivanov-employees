import reader.DataReader;
import model.EmployeeData;
import util.FindPair;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        String path = "src/main/resources/csv.txt";
        String invalidDataPath = "src/main/resources/invalidDataCsv.txt";
        List<EmployeeData> employeeDataList = dataReader.readDataFile(path);
        String result = FindPair.findLongestPeriod(employeeDataList);
        System.out.println(result);
    }
}

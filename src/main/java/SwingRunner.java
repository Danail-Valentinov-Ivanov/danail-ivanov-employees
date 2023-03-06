import model.EmployeeData;
import model.Pair;
import reader.DataReader;
import util.FindPair;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingRunner {
    private static final JFrame frame = new JFrame("Pair of employees who have worked together");
    static File selectedFile = null;
    static List<EmployeeData> employeeDataList = new ArrayList<>();
    static String path = "src/main/resources";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            startGUI();
        });
    }

    private static void startGUI() {
        frame.setBounds(280, 180, 800, 400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar jMenuBar;
        JMenu fileMenu;
        JMenuItem openFileButton;
        JTextArea jTextArea;
        openFileButton = new JMenuItem("Open File");

        fileMenu = new JMenu("File");
        fileMenu.add(openFileButton);
        jMenuBar = new JMenuBar();
        jMenuBar.setBounds(0, 0, 800, 20);
        jMenuBar.add(fileMenu);
        jTextArea = new JTextArea("Welcome to \"Pair of employees who have worked together\" program! \n" +
                "To start the program, press File menu and then press Open File button.");
        jTextArea.setBounds(200, 150, 400, 35);
        frame.add(jMenuBar);
        frame.add(jTextArea);

        DataReader dataReader = new DataReader();

        openFileButton.addActionListener(e -> {
            String excepMessage = null;
            if (e.getSource() == openFileButton) {
                JFileChooser jFileChooser = new JFileChooser(path);
                int returnValue = jFileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jFileChooser.getSelectedFile();
                    if (selectedFile != null) {
                        String pathToFile = selectedFile.getPath();
                        try {
                            employeeDataList = dataReader.readDataFile(pathToFile);
                        } catch (IOException ioException) {
                            excepMessage = ioException.getMessage();
                        } catch (IllegalArgumentException illegalArgumentException) {
                            excepMessage = illegalArgumentException.getMessage();
                        }
                        if (excepMessage == null) {
                            List<Pair> resultList = FindPair.findLongestPeriod(employeeDataList);
                            showResultTableGUI(resultList);
                        } else {
                            showException(excepMessage);
                        }
                    }
                }
            }
        });
    }

    private static void showResultTableGUI(List<Pair> resultList) {
        String[][] dataRecord = new String[resultList.size()][4];
        String[] columns = {"Employee ID #1", "Employee ID #2", "Project ID", "Days worked"};
        for (int i = 0; i < resultList.size(); i++) {
            dataRecord[i][0] = String.valueOf(resultList.get(i).getEmployeeId1());
            dataRecord[i][1] = String.valueOf(resultList.get(i).getEmployeeId2());
            dataRecord[i][2] = String.valueOf(resultList.get(i).getProjectId());
            dataRecord[i][3] = String.valueOf(resultList.get(i).getCommonDays());
        }
        JTable jTable = new JTable(dataRecord, columns);
        jTable.setBounds(30, 40, 200, 300);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        final JFrame frameGUI = new JFrame("Pair of employees who have worked together");
        frameGUI.setBounds(300, 200, 800, 400);
        frameGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameGUI.add(jScrollPane);
        frameGUI.setVisible(true);
    }

    private static void showException(String excepMessage) {
        JOptionPane.showMessageDialog(frame, excepMessage, "Exception Message",
                JOptionPane.WARNING_MESSAGE);
    }
}

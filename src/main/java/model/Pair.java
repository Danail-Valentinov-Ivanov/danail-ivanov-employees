package model;

public class Pair {
    private final int employeeId1;
    private final int employeeId2;
    private final int projectId;
    private final long commonDays;

    public Pair(int employeeId1, int employeeId2, int projectId, long commonDays) {
        this.employeeId1 = employeeId1;
        this.employeeId2 = employeeId2;
        this.projectId = projectId;
        this.commonDays = commonDays;
    }

    public int getEmployeeId1() {
        return employeeId1;
    }

    public int getEmployeeId2() {
        return employeeId2;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getCommonDays() {
        return commonDays;
    }
}

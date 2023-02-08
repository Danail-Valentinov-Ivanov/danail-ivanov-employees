package model;

import java.time.LocalDate;

public class EmployeeData {
    private int employeeId;
    private int projectId;
    private LocalDate localDateFrom;
    private LocalDate localDateTo;

    public EmployeeData(int employeeId, int projectId, LocalDate localDateFrom, LocalDate localDateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.localDateFrom = localDateFrom;
        this.localDateTo = localDateTo;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getLocalDateFrom() {
        return localDateFrom;
    }

    public void setLocalDateFrom(LocalDate localDateFrom) {
        this.localDateFrom = localDateFrom;
    }

    public LocalDate getLocalDateTo() {
        return localDateTo;
    }

    public void setLocalDateTo(LocalDate localDateTo) {
        this.localDateTo = localDateTo;
    }
}

package com.mindex.challenge.data;
import com.mindex.challenge.data.Employee;

public class ReportingStructure { // Required fields, number of reports and employee
    private Employee employee;
    private int numberOfReports;
    
    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }


        
    }
    


package com.mindex.challenge.data;

/*
 * Creates class compensation which tracks pay for employees.
 * Takes in employee, salary, and effective date. 
 * 
 */

public class Compensation {
    private Employee employee;
    private int salary;
    private long effectiveDate;


    public Compensation() {
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}



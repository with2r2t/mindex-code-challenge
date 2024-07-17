package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private Employee employee = new Employee();
    private double salary = 0 ;
    private Date effectiveDate = new Date();


    public Compensation(Employee e,double s, Date effDate){
        this.employee = e;
        this.salary = s;
        this.effectiveDate = effDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

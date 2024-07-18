package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private Employee employee = new Employee();
    private String compensationId;
    private Double salary = 0.0;
    private Date effectiveDate = new Date();


    public Compensation(Employee employee,Double salary, Date effectiveDate){
        this.employee = employee;
        this.compensationId = employee.getEmployeeId();
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getCompensationId() {
        return compensationId;
    }

    public void setEmployeeId(String employeeId) {
        this.compensationId = compensationId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

package com.mindex.challenge.requests;

import java.util.Date;


//Seperate request class created to simplify creation requests
public class CompensationRequest {

    private Double salary;
    private Date effectiveDate;

    public CompensationRequest(){

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

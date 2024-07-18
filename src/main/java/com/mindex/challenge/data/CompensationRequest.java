package com.mindex.challenge.data;

import java.util.Date;

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

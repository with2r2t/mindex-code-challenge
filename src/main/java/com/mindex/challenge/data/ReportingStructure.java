package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ReportingStructure {
    private Employee employee = new Employee();
    private int numberOfReports = 0;

    @Autowired
    private EmployeeRepository employeeRepository;


    public ReportingStructure(Employee e){
        employee = e;
        numberOfReports = 0;
    }


    public Employee getEmployee(){return employee;}

    public void setEmployee(Employee employee){this.employee = employee;}

    public int getNumberOfReports(){ return numberOfReports;}

    public void setNumberOfReports(int numberOfReports){this.numberOfReports = numberOfReports;}

}
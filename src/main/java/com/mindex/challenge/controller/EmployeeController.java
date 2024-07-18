package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationRequest;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    @GetMapping("/employee/{id}/reporting")
    public ReportingStructure repStruct(@PathVariable String id) {
        LOG.debug("Received employee report structure request for id [{}]", id);

        return employeeService.repStruct(id);
    }

    @PostMapping("/employee/{id}/compensation")
    public Compensation createCompensation(@PathVariable String id,
                                           @RequestBody CompensationRequest compensationRequest ){

        Double salary = compensationRequest.getSalary();
        Date effectiveDate = compensationRequest.getEffectiveDate();
        LOG.debug("Received compensation creation request for id [{}], with salary [{}], and effective date [{}]", id,salary,effectiveDate);

        return employeeService.createCompensation(id,salary,effectiveDate);
    }

    @GetMapping("/employee/{id}/compensation")
    public Compensation viewCompensation(@PathVariable String id) {
        LOG.debug("Received employee report structure get request for id [{}]", id);

        return employeeService.viewCompensation(id);
    }
}

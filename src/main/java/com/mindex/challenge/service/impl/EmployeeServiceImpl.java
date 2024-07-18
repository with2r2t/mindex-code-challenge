package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    public ReportingStructure repStruct(String id) {
        LOG.debug("creating employee structure for [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure rs = new ReportingStructure(employee);
        if (employee.getDirectReports() != null) {
            rs.setNumberOfReports(calcReports(employee.getDirectReports()));
        }
        return rs;

    }

    @Override
    public Compensation viewCompensation(String id) {
        LOG.debug("viewing compensation for id [{}]", id);

        Compensation compensation = compensationRepository.findByCompensationId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid compensationId: " + id);
        }

        return compensation;
    }

    @Override
    public Compensation createCompensation(String id, Double salary, Date effectiveDate) {
        LOG.debug("Creating compensation for id [{}]", id);

        Compensation compensation = new Compensation(employeeRepository.findByEmployeeId(id), salary, effectiveDate);
        compensationRepository.insert(compensation);

        return compensation;
    }

    //Method to calculate number of reports an employee has
    //
    //Would have liked to have made it a member function of ReportingStructure but unfamiliarity with MongoDB led to me not knowing how to
    //make the employeeRepository accessible in the ReportingStructure class.
    private int calcReports(List<Employee> reports) {
        int numberOfReports = 0;
        assert employeeRepository != null;

        for (Employee report : reports) {

            report = employeeRepository.findByEmployeeId(report.getEmployeeId());

            if (report.getDirectReports() != null) {
                numberOfReports += 1;
                numberOfReports += calcReports(report.getDirectReports());
            } else {
                numberOfReports += 1;
            }
        }
        return numberOfReports;
    }
}

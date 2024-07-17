package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

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

    public ReportingStructure repStruct(String id){
        LOG.debug("creating employee structure for [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure rs = new ReportingStructure(employee);
        //Quick and dirty solution, current counts employee added so need to subtract that. Would love to call from inside the object class
        //but Autowiring isn't allowing me to use the repository which is needed.
        rs.setNumberOfReports(calcReports(employee)-1);
        return rs;

    }

    private int calcReports(Employee e){
        int numberOfReports= 0;
        //Because employee objects without reports have NULL instead of an empty list, size needs to be used as a checker
        int size = 0;
        if(e.getDirectReports() != null ){
            System.out.println(e.getFirstName());
            numberOfReports += 1;
            for(int i = 0; i < e.getDirectReports().size(); i++ ){
                assert employeeRepository != null;
                numberOfReports += calcReports(employeeRepository.findByEmployeeId(e.getDirectReports().get(i).getEmployeeId()));
            }
        }else{
            numberOfReports += 1;
        }

        return numberOfReports;
    }
}

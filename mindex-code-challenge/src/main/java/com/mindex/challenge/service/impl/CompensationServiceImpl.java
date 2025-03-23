package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationService.class);

    @Autowired
    private CompensationRepository compensationRepository;  //Repository used in order to persist Compensation

    @Autowired
    private EmployeeRepository employeeRepository;


    /*
     * Creates compensation for employee
     * 
     * @param compensation              comprised of employee / effective date
     * @return                          created compensation item
     * @throw IllegalArgumentException  if compensation/employee is null
     * @throw RuntimeException          if employee is not in the repository.
     * 
     */

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation for employee: {}", compensation.getEmployee().getEmployeeId());


        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        if (employee == null) {
            throw new RuntimeException("Employee not found: " + compensation.getEmployee().getEmployeeId());
        }

        compensationRepository.insert(compensation);
        return(compensation);
    }

    /*
     * Retrieves compensation for given employee
     * 
     * @param employeeId            ID of employee to whom compensation belongs
     * @return                      created compensation
     * @throws RuntimeException     no compensation exists for employee
     * 
     */

    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation for employeeId: {}", employeeId);
        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(employeeId);
        if (compensation == null) {
            throw new RuntimeException("Compensation not found for employeeId: " + employeeId);
        }
        return compensation;
    }

    /*
     * Updated compensation for given employee
     * 
     * @param compensation          comprised of Employee / effective date
     * @return                      updated compensation
     * @throws RuntimeException     no compensation exists to update
     */

    public Compensation update(Compensation compensation) {
        LOG.debug("Updating compensation information for employeeId: {}", compensation.getEmployee().getEmployeeId());

        Compensation curCompensation = compensationRepository.findByEmployee_EmployeeId(compensation.getEmployee().getEmployeeId());
        if (curCompensation == null) {
            throw new RuntimeException("No compensation available to update.");
        }

        curCompensation.setSalary(compensation.getSalary());
        curCompensation.setEffectiveDate(compensation.getEffectiveDate());

        return compensationRepository.save(curCompensation);
    }
}
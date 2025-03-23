package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    @Autowired
    private EmployeeService employeeService;

    /*
     * Creates reporting structure for employee
     * 
     * @param employee          Contains the employee Type
     * @param employeeId        Employee's ID
     * 
     * @return                  New reporting structure created
     * 
     * @throw RuntimeException  If no employee is found with this ID
     */
    
    @Override 
    public ReportingStructure create(Employee employee, String employeeId) {
    Employee employeeDetails = employeeService.read(employeeId);
    
    if (employeeDetails == null) {
    	throw new RuntimeException("Employee not found with ID: " + employeeId);
    	}
    	
    	int totalReports = countReports(employeeDetails, new HashSet<>());
    	
    	return new ReportingStructure(employeeDetails, totalReports);
    	}
    	    
    /*
     * Reads the reporting structure for the given employee
     * 
     * @param employeeId    Employee's ID
     * 
     * @return              The number of collective reports that given employee has, or 0 if none.
     */
    @Override 
    public ReportingStructure read(String employeeId) {
    Employee employee = employeeService.read(employeeId);
    int totalReports = countReports(employee, new HashSet<>());
    return new ReportingStructure(employee, totalReports);
    }
    private int countReports(Employee employee, Set<String> visited) { //Simple recursive call to count reports through reporting structure
        if(employee.getDirectReports() == null) {
            return 0;
        }
        int count = 0;
        for(Employee report : employee.getDirectReports()) {
            if  (report !=null && visited.add(report.getEmployeeId())) {
                Employee fullReport = employeeService.read(report.getEmployeeId());
                count +=1 + countReports(fullReport, visited);
        }
    }
    return count;
    }

    /*
     * Gets employee's ID
     * 
     * @param employeeId        Employee's ID
     * 
     * @return                  Updated reporting structure based on employee and total reports
     * 
     * @throw RuntimeException  If no employee found with said ID
     */

    @Override 
        public ReportingStructure getReportingStructure(String employeeId) {
        Employee employee = employeeService.read(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID:" + employeeId);
        }

        int totalReports = countReports(employee, new HashSet<>());
        return new ReportingStructure(employee, totalReports);
    }
    

    

}
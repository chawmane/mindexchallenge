package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;


public interface ReportingStructureService {
    ReportingStructure create(Employee employee, String employeeId);
    ReportingStructure read(String employeeId);
    ReportingStructure getReportingStructure(String employeeId); 
}
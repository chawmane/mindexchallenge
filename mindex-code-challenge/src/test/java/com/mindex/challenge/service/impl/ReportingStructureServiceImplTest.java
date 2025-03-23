package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;


class ReportingStructureServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private ReportingStructureServiceImpl reportingStructureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReportingStructure() {
        Employee employee = new Employee();
        employee.setEmployeeId("1234");
        employee.setFirstName("John");

        Employee report1 = new Employee();
        report1.setEmployeeId( "4382");

        Employee report2 = new Employee();
        report2.setEmployeeId("4u312");

        employee.setDirectReports(Arrays.asList(report1, report2));
        when(employeeRepository.findByEmployeeId("1234")).thenReturn(employee);
        when(employeeRepository.findByEmployeeId("4382")).thenReturn(report2);
        when(employeeRepository.findByEmployeeId("4u312")).thenReturn(report2);

        ReportingStructure createdStructure = reportingStructureService.create(employee, "1234");

        assertNotNull(createdStructure);
        assertEquals("1234", createdStructure.getEmployee().getEmployeeId());
        assertEquals(2, createdStructure.getNumberOfReports());
        
        verify(employeeRepository, times(1)).findByEmployeeId("1234");
        verify(employeeRepository, times(1)).findByEmployeeId("4382");
        verify(employeeRepository, times(1)).findByEmployeeId("4u312");

    }
}
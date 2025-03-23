package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

class CompensationServiceImplTest {

    @Mock
    private CompensationRepository compensationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private CompensationServiceImpl compensationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCompensation() {
        Employee employee = new Employee();
        employee.setEmployeeId("12345");

        Compensation compensation = new Compensation();
        compensation.setEmployee(employee);
        compensation.setSalary(100000);
        compensation.setEffectiveDate(1672531200L);

        when(employeeRepository.findByEmployeeId("12345")).thenReturn(employee);
        when(compensationRepository.insert(compensation)).thenReturn(compensation);

        Compensation createdCompensation = compensationService.create(compensation);

        assertNotNull(createdCompensation);
        assertEquals(100000, createdCompensation.getSalary());
        verify(compensationRepository, times(1)).insert(compensation);
    }

    @Test
    void testReadCompensation() {
        Compensation compensation = new Compensation();
        compensation.setSalary(100000);
        compensation.setEffectiveDate(1672531200L);

        when(compensationRepository.findByEmployee_EmployeeId("12345")).thenReturn(compensation);

        Compensation foundCompensation = compensationService.read("12345");

        assertNotNull(foundCompensation);
        assertEquals(100000, foundCompensation.getSalary());
        verify(compensationRepository, times(1)).findByEmployee_EmployeeId("12345");
    }
}
package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportingStructure")

public class ReportingStructureController {
    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/{employeeId}")
    public ReportingStructure getReportingStructure(@PathVariable String employeeId) {
        return reportingStructureService.getReportingStructure(employeeId);
    }
}
package com.example.lld.practise.designPatterns.FacadeDesigPattern.EmployeeScenario;

import com.example.lld.practise.EmployeeAudit.Division;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Employee {
    int employeeId;
    int emailId;
    Division division;
}

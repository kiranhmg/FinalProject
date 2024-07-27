package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.EmployeeDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto findByEmail(String email);
    void generateAndSendOtp(EmployeeDto employeeDto);
    boolean validateOtp(String otp,EmployeeDto employeeDto);
    List<RiseComplaintDto> findComplaintByEmpId(int id);

}

package com.xworkz.finalproject.model.repo;


import com.xworkz.finalproject.dto.EmployeeDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;

import java.util.List;

public interface EmployeeRepo {
    EmployeeDto findByEmail(String email);
    boolean updateEmployeeOtp(EmployeeDto employeeDto);
    List<RiseComplaintDto> findComplaintByEmpId(int id);
}

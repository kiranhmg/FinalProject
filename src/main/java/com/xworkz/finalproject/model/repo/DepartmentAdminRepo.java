package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.*;

import java.util.List;

public interface DepartmentAdminRepo {
    AddDepartmentAdminDto findByEmail(String email);
    DepartmentDto findByIssue1(String issue);
    List<RiseComplaintDto> findByIssue(String issue);
    boolean saveEmployee(EmployeeDto employeeDto);
    EmployeeDto findByEmailOrPhone(EmployeeDto employeeDto);
    List<DepartmentDto> departmrntList();
    DepartmentDto findById(int id);
    List<RiseComplaintDto> findByDeptId(int id);
    List<EmployeeDto> findEmpByDeptId(int id);

}

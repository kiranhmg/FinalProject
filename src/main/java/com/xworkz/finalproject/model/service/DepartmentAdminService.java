package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.*;

import java.util.List;

public interface DepartmentAdminService {
    AddDepartmentAdminDto findByEmail(String email);
    List<RiseComplaintDto> findByIssue(String issue);
    boolean saveEmployee(EmployeeDto employeeDto);
    List<DepartmentDto> departmrntList();
    DepartmentDto findById(int id);
    List<RiseComplaintDto> findByDeptId(int id);
    public List<EmployeeDto> findEmpByDeptId(int id);

}

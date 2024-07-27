package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.*;

import java.util.List;
import java.util.Optional;

public interface AdminRepo {
    Optional<AdminDto> saveAdmin(AdminDto adminDto);
    AdminDto findByEmailAndPassword(AdminDto adminDto);
    List<SignUpDto> viewSignUp(SignUpDto signUpDto);
    DepartmentDto findDepartment(String type);
    ComplaintHistory saveHistory(ComplaintHistory complaintHistory);
    AddDepartmentAdminDto findByEmailOrPhone(AddDepartmentAdminDto adminDto);
    boolean saveDepartmentAdmins(AddDepartmentAdminDto adminDto);
    List<RiseComplaintDto> findAllComplaints();


}

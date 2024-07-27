package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.*;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminDto> saveAdmin(AdminDto adminDto);
    List<SignUpDto> viewSignUp(SignUpDto signUpDto);
    AdminDto findByEmailAndPassword(AdminDto adminDto);
    DepartmentDto findDepartment(String type);
    ComplaintHistory saveHistory(ComplaintHistory complaintHistory);
    boolean saveDepartmentAdmins(AddDepartmentAdminDto adminDto);
    List<RiseComplaintDto> findAllComplaints();




}

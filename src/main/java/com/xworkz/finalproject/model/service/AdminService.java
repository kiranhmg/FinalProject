package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.AdminDto;
import com.xworkz.finalproject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminDto> saveAdmin(AdminDto adminDto);
    List<SignUpDto> viewSignUp(SignUpDto signUpDto,AdminDto adminDto);

}

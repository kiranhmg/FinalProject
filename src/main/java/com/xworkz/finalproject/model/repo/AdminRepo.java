package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.AdminDto;
import com.xworkz.finalproject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface AdminRepo {
    Optional<AdminDto> saveAdmin(AdminDto adminDto);
    AdminDto findByEmailAndPassword(AdminDto adminDto);
    List<SignUpDto> viewSignUp(SignUpDto signUpDto);


}

package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.SignUpDto;

import java.util.Optional;

public interface SignUpService {
    default Optional<SignUpDto> save(SignUpDto signUpDto){
        return Optional.empty();
    }
    Optional<SignUpDto> searchByEmailAndPassword(String email,String password);
    Optional<SignUpDto> updateLoginCount(SignUpDto signUpDto,String email);
    SignUpDto searchByEmail1(String email);
    SignUpDto updateAccountLock(SignUpDto signUpDto);
    SignUpDto updatePasswordAndUserPassword(SignUpDto signUpDto);
    SignUpDto updatePassword(SignUpDto signUpDto);




}

package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.SignUpDto;

import java.util.List;
import java.util.Optional;

public interface SignUpRepo {
    default Optional<SignUpDto> save(SignUpDto signUpDto){
        return Optional.empty();
    }
    boolean searchByEmail(SignUpDto signUpDto);
    boolean searchByPhone(SignUpDto signUpDto);
    Optional<SignUpDto> searchByEmailAndPassword(String email,String password);
    Optional<SignUpDto> updateLoginCount(SignUpDto signUpDto,String email);
   SignUpDto searchByEmail1(String email);
   SignUpDto updateAccountLock(SignUpDto signUpDto);
   SignUpDto updatePasswordAndUserPassword(SignUpDto signUpDto);
   SignUpDto updatePassword(SignUpDto signUpDto);
    boolean searchByPhone1(Long phone);

}

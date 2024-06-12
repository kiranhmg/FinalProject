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
}

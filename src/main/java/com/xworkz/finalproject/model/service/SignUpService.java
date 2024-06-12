package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.SignUpDto;

import java.util.Optional;

public interface SignUpService {
    default Optional<SignUpDto> save(SignUpDto signUpDto){
        return Optional.empty();
    }
}

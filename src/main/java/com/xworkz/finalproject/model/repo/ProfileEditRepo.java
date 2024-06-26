package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.SignUpDto;

public interface ProfileEditRepo {
    SignUpDto updateProfileDetails(SignUpDto signUpDto);
    SignUpDto searchByEmailAndPhone(SignUpDto signUpDto);
}

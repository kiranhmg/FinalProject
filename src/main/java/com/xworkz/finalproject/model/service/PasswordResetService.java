package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.PasswordResetDto;
import com.xworkz.finalproject.dto.SignUpDto;

import java.util.Optional;

public interface PasswordResetService {
    boolean updateUserPassword(PasswordResetDto passwordResetDto);


}

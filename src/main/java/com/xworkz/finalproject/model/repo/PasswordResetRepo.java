package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.PasswordResetDto;
import com.xworkz.finalproject.dto.SignUpDto;

import java.util.Optional;

public interface PasswordResetRepo {
    boolean updateUserPassword(PasswordResetDto passwordResetDto);
}

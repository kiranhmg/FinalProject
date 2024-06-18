package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.PasswordResetDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.repo.PasswordResetRepo;
import com.xworkz.finalproject.model.repo.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    private PasswordResetRepo passwordResetRepo;
    @Autowired
    private SignUpRepo signUpRepo;

    @Override
    public boolean updateUserPassword(PasswordResetDto passwordResetDto) {
        Optional<SignUpDto> optional=signUpRepo.searchByEmailAndPassword(passwordResetDto.getEmail(), passwordResetDto.getPassword());
        if(optional.isPresent()) {
            if (passwordResetDto.getNewpassword().equals(passwordResetDto.getConfirmpassword())) {
                boolean result = passwordResetRepo.updateUserPassword(passwordResetDto);
                if (result) {
                    System.out.println("updated success fully");
                    return true;
                } else {
                    System.out.println("not updated success fully");
                    return false;
                }
            } else {
                System.out.println("please confirm password correctly");
                return false;
            }
        }
        else {
            System.out.println("email and password not valid");
        }
        return false;
    }
}

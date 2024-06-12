package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.repo.SignUpRepo;
import com.xworkz.finalproject.source.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Autowired
    private SignUpRepo signUpRepo;
    @Override
    public Optional<SignUpDto> save(SignUpDto signUpDto) {

        System.out.println("save inside service");
        String password= PasswordGenerator.generatePassword();
        signUpDto.setPassword(password);


        boolean list=signUpRepo.searchByEmail(signUpDto);
        boolean list1=signUpRepo.searchByPhone(signUpDto);

        if(list) {
            System.out.println("mail already registered");

                return Optional.empty();

        }  if (list1) {
            System.out.println("phone already registered");
            return Optional.empty();
        }
        else {
            Optional<SignUpDto> optional=signUpRepo.save(signUpDto);
            if (optional.isPresent()){
                System.out.println("data is valid and saved");
            }
            else {
                System.out.println("data is not valid");
                return Optional.empty();
            }
        }

        return Optional.ofNullable(signUpDto);
    }
}

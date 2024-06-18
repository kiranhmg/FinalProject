package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.AdminDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;

    @Override
    public Optional<AdminDto> saveAdmin(AdminDto adminDto) {
        AdminDto optional = adminRepo.findByEmailAndPassword(adminDto);
        if (optional != null) {
            System.out.println("email or password exists");
            return Optional.empty();
        } else {
            Optional<AdminDto> optional1 = adminRepo.saveAdmin(adminDto);
            if (optional1.isPresent()) {
                System.out.println("admin data is valid");
                return Optional.ofNullable(adminDto);
            } else {
                System.out.println("admin not saved");
                return Optional.empty();
            }
        }
    }

    @Override
    public List<SignUpDto> viewSignUp(SignUpDto signUpDto, AdminDto adminDto) {
        AdminDto adminDto1 = adminRepo.findByEmailAndPassword(adminDto);
        if (adminDto1==null) {
            System.out.println("email or password exists");
            return Collections.emptyList();
        } else {

            List<SignUpDto> list = adminRepo.viewSignUp(signUpDto);
            if (!list.isEmpty()) {
                System.out.println("sign up details fetching");
                return list;
            } else {
                System.out.println("sign up details not found");
                return Collections.emptyList();
            }
        }


    }
}

package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.*;
import com.xworkz.finalproject.model.repo.AdminRepo;
import com.xworkz.finalproject.model.repo.DepartmentAdminRepo;
import com.xworkz.finalproject.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private DepartmentAdminRepo departmentAdminRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(AddDepartmentAdminDto adminDto){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(adminDto.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear "+adminDto.getName()+" "+
                " your signup success Please SignIn through this password :"+adminDto.getPassword()+"\n\n"+
                "Thanks and Regards,\n"+" "+"X-workz Team");
        mailSender.send(simpleMailMessage);
    }


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
    public List<SignUpDto> viewSignUp(SignUpDto signUpDto) {

        List<SignUpDto> list = adminRepo.viewSignUp(signUpDto);
            if (!list.isEmpty()) {
                System.out.println("sign up details fetching");
                return list;
            } else {
                System.out.println("sign up details not found");
                return Collections.emptyList();
            }
        }




    @Override
    public AdminDto findByEmailAndPassword(AdminDto adminDto) {
        AdminDto adminDto1=adminRepo.findByEmailAndPassword(adminDto);
        if(adminDto1!=null)
        {
            System.out.println("email and password valid");
            return adminDto1;
        }
        else {
            System.out.println("email and password not found");
            return null;
        }
    }

    @Override
    public DepartmentDto findDepartment(String type) {
        DepartmentDto departmentDto=adminRepo.findDepartment(type);
        if(departmentDto!=null)
        {
            return departmentDto;
        }
        else {
            return null;
        }
    }

    @Override
    public ComplaintHistory saveHistory(ComplaintHistory complaintHistory) {
        ComplaintHistory complaintHistory1=adminRepo.saveHistory(complaintHistory);
        if(complaintHistory1!=null)
        {
            System.out.println("history saved success fully");
            return complaintHistory1;
        }
        else {
            System.out.println("history not saved");
            return null;
        }
    }

    @Override
    public boolean saveDepartmentAdmins(AddDepartmentAdminDto adminDto) {
        AddDepartmentAdminDto adminDto1=adminRepo.findByEmailOrPhone(adminDto);
        if (adminDto1!=null)
        {
            System.out.println("email or phone exists");
            return false;
        }
        else {
            DepartmentDto departmentDto=departmentAdminRepo.findByIssue1(adminDto.getIssue());
            adminDto.setDeptId(departmentDto.getId());
            String password= PasswordGenerator.generatePassword();
            adminDto.setPassword(password);
            sendEmail(adminDto);
            String encodePassWord=passwordEncoder.encode(password);
            adminDto.setPassword(encodePassWord);
            boolean result=adminRepo.saveDepartmentAdmins(adminDto);
            if(result)
            {
                return true;
            }
            else {
                return false;
            }

        }
    }
}

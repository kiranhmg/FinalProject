package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.AddDepartmentAdminDto;
import com.xworkz.finalproject.dto.EmployeeDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.model.repo.EmployeeRepo;
import com.xworkz.finalproject.util.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmployeeDto adminDto){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(adminDto.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear "+adminDto.getName()+" "+
                " your signup success Please SignIn through this otp :"+adminDto.getPassword()+"\n\n"+
                "Thanks and Regards,\n"+" "+"X-workz Team");
        mailSender.send(simpleMailMessage);
    }
    @Override
    public EmployeeDto findByEmail(String email) {
        EmployeeDto employeeDto=employeeRepo.findByEmail(email);
        if (employeeDto!=null)
        {
            return employeeDto;
        }
        else {
            return null;
        }
    }

    @Override
    public void generateAndSendOtp(EmployeeDto employeeDto) {
        String password= OtpGenerator.generatePassword();
        employeeDto.setPassword(password);
        sendEmail(employeeDto);
        employeeRepo.updateEmployeeOtp(employeeDto);
    }

    @Override
    public boolean validateOtp(String otp, EmployeeDto employeeDto) {
        if(employeeDto.getPassword().equals(otp))
        {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<RiseComplaintDto> findComplaintByEmpId(int id) {
        List<RiseComplaintDto> list=employeeRepo.findComplaintByEmpId(id);
        if(!list.isEmpty())
        {
            return list;
        }
        else {
            return Collections.emptyList();
        }
    }
}

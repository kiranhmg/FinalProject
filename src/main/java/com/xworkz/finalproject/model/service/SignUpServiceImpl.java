package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.repo.SignUpRepo;
import com.xworkz.finalproject.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Autowired
    private SignUpRepo signUpRepo;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(SignUpDto signupDTO){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(signupDTO.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear "+signupDTO.getFirstName()+" "+signupDTO.getLastName()+
                " your signup success Please SignIn through this password :"+signupDTO.getPassword()+"\n\n"+
                "Thanks and Regards,\n"+" "+"X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }

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

        }
        if (list1) {
            System.out.println("phone already registered");
            return Optional.empty();
        }
        else {
            Optional<SignUpDto> optional=signUpRepo.save(signUpDto);
            if (optional.isPresent()){
                System.out.println("data is valid and saved");
                sendEmail(signUpDto);
                return Optional.ofNullable(signUpDto);
            }
            else {
                System.out.println("data is not valid");
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<SignUpDto> searchByEmailAndPassword(String email, String password) {
        Optional<SignUpDto> optional=signUpRepo.searchByEmailAndPassword(email, password);
        if(optional.isPresent())
        {
            System.out.println("email and password is present");
            SignUpDto signUpDto=optional.get();
            return Optional.ofNullable(signUpDto);
        }
        else {
            System.out.println("email and password is not present");
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignUpDto> updateLoginCount(SignUpDto signUpDto,String email) {
        Optional<SignUpDto> optional=signUpRepo.updateLoginCount(signUpDto,email);
        if (optional.isPresent())
        {
            System.out.println("count increamented");
            return Optional.ofNullable(signUpDto);
        }
        else {
            System.out.println("count not increamented");
            return Optional.empty();
        }
    }

    @Override
    public SignUpDto searchByEmail1(String email) {
        SignUpDto optional=signUpRepo.searchByEmail1(email);
        if(optional!=null)
        {
            System.out.println("mail already present");
            return optional;
        }
        else {
            System.out.println("mail not present");
            return null;
        }
    }

    @Override
    public SignUpDto updateAccountLock(SignUpDto signUpDto) {
        SignUpDto signUpDto1=signUpRepo.updateAccountLock(signUpDto);
        if (signUpDto1!=null)
        {
            System.out.println("updating account lock");
            return signUpDto;
        }
        else {
            System.out.println("account lock not updated");
            return null;
        }
    }

    @Override
    public SignUpDto updatePasswordAndUserPassword(SignUpDto signUpDto) {
        SignUpDto signUpDto1=signUpRepo.updatePasswordAndUserPassword(signUpDto);
        if (signUpDto1!=null)
        {
            System.out.println("updating password and user pass");
            return signUpDto;
        }
        else {
            System.out.println("not updated");
            return null;
        }

    }

    @Override
    public SignUpDto updatePassword(SignUpDto signUpDto) {
        SignUpDto signUpDto1=signUpRepo.updatePassword(signUpDto);
        if (signUpDto1!=null)
        {
            sendEmail(signUpDto);
            System.out.println("updated new password");
            return signUpDto1;
        }
        else {
            System.out.println("not updated new password");
            return null;
        }
    }

    @Override
    public boolean searchByPhone1(Long phone) {
        boolean result=signUpRepo.searchByPhone1(phone);
        if (result)
        {
            return true;
        }
        else {
            return false;
        }
    }
}

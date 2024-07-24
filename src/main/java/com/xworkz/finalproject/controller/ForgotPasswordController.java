package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.SignUpService;
import com.xworkz.finalproject.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@RequestMapping("/")
public class ForgotPasswordController {
    @Autowired
    private SignUpService signUpService;
    public ForgotPasswordController()
    {
        System.out.println("forgot password created");
    }
    @PostMapping("/forgot")
    public String forgotPassword(@RequestParam String email, Model model)
    {
        SignUpDto signUpDto=signUpService.searchByEmail1(email);
        if(signUpDto!=null)
        {
            String password= PasswordGenerator.generatePassword();
            signUpDto.setPassword(password);
            SignUpDto signUpDto1=signUpService.updatePassword(signUpDto);
            System.out.println("new password sent to registered mail id");
            return "PasswordResetPage";

        }
        else {
            System.out.println("new password not generated");
            model.addAttribute("msg","please enter registered email id");
            return "ForgogtPassword";
        }

    }

}

package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.PasswordResetDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.PasswordResetService;
import com.xworkz.finalproject.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class SignInController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private PasswordResetService passwordResetService;

    private int accCount=3;
    public SignInController()
    {
        System.out.println("sign in controller created");
    }

    @PostMapping("/signindata")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model,SignUpDto signUpDto)
    {
        System.out.println(signUpDto);
        Optional<SignUpDto> optional=signUpService.searchByEmailAndPassword(email,password);
        if(optional.isPresent()) {
            System.out.println("email and password valid");
            model.addAttribute("msg", "email and password valid");
            model.addAttribute("action", "retain");
            model.addAttribute("key", optional.get());

            SignUpDto emailCount = signUpService.searchByEmail1(email);
            emailCount.setAccountLock(3);
            signUpService.updateAccountLock(emailCount);
            if (emailCount.getCountLogin() == 0) {
                emailCount.setCountLogin(emailCount.getCountLogin() + 1);
                Optional<SignUpDto> optional2 = signUpService.updateLoginCount(emailCount, email);
                return "PasswordResetPage";
            } else {
                emailCount.setCountLogin(emailCount.getCountLogin() + 1);
                Optional<SignUpDto> optional2 = signUpService.updateLoginCount(emailCount, email);

                return "index";
            }
        }
        else {
            SignUpDto signUpDto1=signUpService.searchByEmail1(email);
            if(signUpDto1.getAccountLock()>1&&signUpDto1.getAccountLock()<4)
            {
                signUpDto1.setAccountLock(signUpDto1.getAccountLock()-1);
                signUpService.updateAccountLock(signUpDto1);

                System.out.println("email and password not valid");
                model.addAttribute("msg","email or password not valid");
                model.addAttribute("msg1","you have left"+signUpDto1.getAccountLock()+"attempts only");
                return "SignIn";
            }
            else {
                model.addAttribute("msg2","sorry your accont is locked please click forgot password");
                signUpDto1.setPassword(null);
                signUpDto1.setUserPassword(null);
                    signUpService.updatePasswordAndUserPassword(signUpDto1);
                return "SignIn";
            }

        }

    }
}

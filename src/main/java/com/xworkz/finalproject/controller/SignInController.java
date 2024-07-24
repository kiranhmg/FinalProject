package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.ImageUploadDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

@Controller
@RequestMapping("/")
public class SignInController {
    private  Logger log = LogManager.getLogger(SignInController.class);

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private ProfileEditService profileEditService;
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private RiseComplaintService riseComplaintService;

    private int accCount=3;
    public SignInController()
    {
        log.info("Test");
    }

    @PostMapping("/signindata")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model, SignUpDto signUpDto,HttpSession session) throws IOException {
        System.out.println(signUpDto);
        Optional<SignUpDto> optional=signUpService.searchByEmailAndPassword(email,password);
        if(optional.isPresent()) {
            System.out.println("email and password valid");
            model.addAttribute("msg", "email and password valid");
            model.addAttribute("action", "retain");
            model.addAttribute("key", optional.get());
            Optional<List<ImageUploadDto>> imageUploadDto=imageUploadService.findByUserId(optional.get().getId());
            if(imageUploadDto.isPresent())
            {
                for (ImageUploadDto img:imageUploadDto.get()
                ) {
                    if(img.getStatus().equals("active")|| img.getStatus()=="active") {
                        session.setAttribute("profilefind", "/profile/" + img.getName());
                    }
                }
            }
            else {
                session.setAttribute("profilefind", "/profile/" +"profile1");
            }
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


                session.setAttribute("email",email);
                session.setAttribute("firstName",emailCount.getFirstName());
                session.setAttribute("lastName",emailCount.getLastName());
                session.setAttribute("phone",emailCount.getPhone());
                session.setAttribute("action","edit");
          List<RiseComplaintDto> optional1=riseComplaintService.searchByUid(emailCount.getId());
          session.setAttribute("dto",optional1);

                model.addAttribute("action","edit");

                return "UserhomePage";
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
                model.addAttribute("msg2","sorry your account is locked please click forgot password");
                signUpDto1.setPassword(null);
                signUpDto1.setUserPassword(null);
                    signUpService.updatePasswordAndUserPassword(signUpDto1);
                return "SignIn";
            }

        }

    }
}

package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class SignUpController {
    @Autowired
    private SignUpService signUpService;
    public SignUpController()
    {
        System.out.println("sign up controller created");
    }
@PostMapping("/signupdata")
        public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model)
        {
            if(bindingResult.hasErrors())
            {
                bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
                model.addAttribute("valid",bindingResult.getAllErrors());
                model.addAttribute("dto",signUpDto);
            }
            else {

                String fname=signUpDto.getFirstName()+" "+signUpDto.getLastName();
                signUpDto.setCreatedBy(fname);
//                signUpDto.setUpdatedBy(fname);
                signUpDto.setCreatedDate(LocalDate.now());
//                signUpDto.setUpdatedDate(LocalDate.now());
                signUpDto.setCountLogin(0);
                signUpDto.setAccountLock(3);
                Optional<SignUpDto> optional=signUpService.save(signUpDto);
                if(optional.isPresent())
                {
                    System.out.println("sign up is success");
                    System.out.println(signUpDto);
                    model.addAttribute("success","your sign up is success :"+signUpDto.getFirstName());

                }
                else {
                    System.out.println("sign up is failed");
                    model.addAttribute("msg","sign up failed "+signUpDto.getFirstName());
                    model.addAttribute("failed","already email or phone registered");
                }

            }
            return "SignUp";
        }
}

package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.PasswordResetDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PasswordResetController {
    @Autowired
    private PasswordResetService passwordResetService;
public PasswordResetController()
{
    System.out.println("PasswordResetController is created");
}
@PostMapping("/setpassword")
    public String passwordRestController(@Valid PasswordResetDto passwordResetDto, BindingResult bindingResult, Model model)
   {
       if(bindingResult.hasErrors())
       {
           bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
           model.addAttribute("errormsg",bindingResult.getAllErrors());
           model.addAttribute("dto",passwordResetDto);
       }
      boolean result=passwordResetService.updateUserPassword(passwordResetDto);
       if (result)
       {
           System.out.println(passwordResetDto);
           System.out.println("password updated success fully");
           model.addAttribute("msg","password updated success fully");

           return "SignIn";
       }
       else {
           System.out.println("password not updated success fully");
           model.addAttribute("msg","password not updated");

       }


    return "PasswordResetPage";
}
}

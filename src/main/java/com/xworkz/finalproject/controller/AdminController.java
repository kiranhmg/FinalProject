package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.AdminDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    public AdminController()
    {
        System.out.println("admin controller is created");
    }
@PostMapping("/adminlog")
    public String adminSave(@Valid AdminDto adminDto, BindingResult bindingResult, Model model,SignUpDto signUpDto)
    {
        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("valid",bindingResult.getAllErrors());
            model.addAttribute("dto",adminDto);
        }
        else {
            List<SignUpDto> list=adminService.viewSignUp(signUpDto,adminDto);
            if (!list.isEmpty())
            {

                model.addAttribute("result", "result is found");
                model.addAttribute("list", list);
                  return "AdminSignUpView";
            }
            else {
                model.addAttribute("result","result not found");

            }

        }
        return "admin";

    }
}

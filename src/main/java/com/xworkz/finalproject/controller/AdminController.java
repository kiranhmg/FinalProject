package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.AdminDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.AdminService;
import com.xworkz.finalproject.model.service.AdminViewComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminViewComplaintService adminViewComplaintService;
    public AdminController()
    {
        System.out.println("admin controller is created");
    }

    @PostMapping("/adminlogin")
    public String login(@Valid AdminDto adminDto,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("valid", bindingResult.getAllErrors());
            model.addAttribute("dto", adminDto);
        } else {
            AdminDto adminDto1 = adminService.findByEmailAndPassword(adminDto);
            if (adminDto1 != null) {
                System.out.println("admin login success");
                return "AdminLoginSuccess";
            } else {
                System.out.println("admin login fail");
                return "admin";
            }
        }
        return "admin";
    }

@RequestMapping(value = "/adminlog",method = {RequestMethod.GET,RequestMethod.POST})
    public String adminSave( Model model,SignUpDto signUpDto)
    {


            List<SignUpDto> list=adminService.viewSignUp(signUpDto);
            if (!list.isEmpty())
            {

                model.addAttribute("result", "result is found");
                model.addAttribute("list", list);
                  return "AdminSignUpView";
            }
            else {
                model.addAttribute("result","result not found");
                return "admin";
            }

        }
@PostMapping("/adminviewcomplaint")
    public String viewComplaints(@RequestParam String issueType,@RequestParam String area,Model model)
{
    List<RiseComplaintDto> list=adminViewComplaintService.findByIssueAndArea(issueType, area);
    if (!list.isEmpty())
    {
        System.out.println("fetching data");
        model.addAttribute("list",list);
        return "AdminViewUserComplaint";

    }
    else {
        model.addAttribute("msg","data not found");
        List<RiseComplaintDto> riseComplaintDtos=adminViewComplaintService.findByIssueOrArea(issueType,area);
        if(!riseComplaintDtos.isEmpty())
        {
            System.out.println("fetching data");
            model.addAttribute("list",riseComplaintDtos);
            return "AdminViewUserComplaint";
        }
        else {
            model.addAttribute("msg","data not found");
        }
    }
    return "AdminViewUserComplaint";
}

    }


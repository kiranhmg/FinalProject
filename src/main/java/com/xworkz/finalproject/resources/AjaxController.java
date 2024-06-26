package com.xworkz.finalproject.resources;

import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class AjaxController {
    @Autowired
    private SignUpService signUpService;
    @GetMapping("/validateEmail")
    public String validateEmail(@RequestParam String email) {
        SignUpDto optional = signUpService.searchByEmail1(email);
        return optional!=null ? "Email is already Exist" : "";
    }

    @GetMapping("/validatePhone")
    public String validatePhone(@RequestParam Long phone) {
        boolean existingPhone = signUpService.searchByPhone1(phone);
        return existingPhone == true ? "Phone is already Exist": ""; // Return true if phone number already exists
    }
}

package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.RiseComplaintService;
import com.xworkz.finalproject.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class RiseComplaintController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private RiseComplaintService riseComplaintService;
    public RiseComplaintController()
    {
        System.out.println("rise complaint is created");
    }
    @PostMapping("/risecomplaint")
    public String saveComplaint(@Valid RiseComplaintDto riseComplaintDto, BindingResult bindingResult, Model model, HttpSession session)
    {
        if (bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("valid",bindingResult.getAllErrors());
            model.addAttribute("dto",riseComplaintDto);
        }
        else {
            String email=(String) session.getAttribute("email");
            SignUpDto signUpDto=signUpService.searchByEmail1(email);
            if(signUpDto!=null)
            {
                riseComplaintDto.setCreatedAt(LocalDateTime.now());
                riseComplaintDto.setCreatedBy(signUpDto.getFirstName());
                riseComplaintDto.setUserId(signUpDto.getId());
                riseComplaintDto.setStatus("active");
                Optional<RiseComplaintDto> optional=riseComplaintService.saveComplaint(riseComplaintDto);

                if (optional.isPresent())
                {
                    System.out.println("complaint registered success fully");
                    model.addAttribute("msg","complaint registered success fully");
                    return "RiseComplaint";

                }
                else {
                    System.out.println("complaint registered not registered");
                    model.addAttribute("msg","complaint not registered");
                    return "RiseComplaint";
                }
            }
            else {
                model.addAttribute("msg","complaint not registered");

            }

        }
        return "RiseComplaint";
    }


    @PostMapping("/viewcomplaint")
    public String viewComplaint(RiseComplaintDto riseComplaintDto,HttpSession session,Model model)
    {

        List<RiseComplaintDto> riseComplaintDtos=(List<RiseComplaintDto>) session.getAttribute("dto");

       if (!riseComplaintDtos.isEmpty())
       {
           model.addAttribute("list",riseComplaintDtos);
           return "ViewComplaint";
       }
       else {

           return "ViewComplaint";
       }

    }
}

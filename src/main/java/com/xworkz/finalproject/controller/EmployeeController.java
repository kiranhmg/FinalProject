package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.ComplaintHistory;
import com.xworkz.finalproject.dto.EmployeeDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.model.service.AdminService;
import com.xworkz.finalproject.model.service.DepartmentAdminService;
import com.xworkz.finalproject.model.service.EmployeeService;
import com.xworkz.finalproject.model.service.RiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {
   @Autowired
   private EmployeeService employeeService;
   @Autowired
   private RiseComplaintService riseComplaintService;
   @Autowired
   private AdminService adminService;
    @PostMapping("/getOtp")
    public String generateOtp(@RequestParam String email, Model model)
    {
        EmployeeDto employeeDto= employeeService.findByEmail(email);
        if (employeeDto!=null)
        {
            employeeService.generateAndSendOtp(employeeDto);
            model.addAttribute("msg","email present");
        }
        else {
            model.addAttribute("msg","email not present");

        }
        return "EmployeeLogin";
    }

    @PostMapping("/loginWithOtp")
    public String validateOtp(@RequestParam String email, @RequestParam String otp, Model model,HttpSession session)
    {
        EmployeeDto employeeDto= employeeService.findByEmail(email);
        List<RiseComplaintDto> list=employeeService.findComplaintByEmpId(employeeDto.getId());

        if(employeeDto!=null)
        {
            boolean result=employeeService.validateOtp(otp,employeeDto);
            if(result)
            {
                session.setAttribute("emplist",list);
                return "EmployeeLoginSuccess";
            }
            else {
                model.addAttribute("msg","please enter valid otp");
                return "EmployeeLogin";
            }

        }

        return "EmployeeLogin";
    }

    @GetMapping("/empviewcomplaint")
    public String empViewComplaints(HttpSession session,Model model)
    {
       List<RiseComplaintDto> riseComplaintDtos=(List<RiseComplaintDto>) session.getAttribute("emplist");
       model.addAttribute("list",riseComplaintDtos);
       return "EmployeeViewComplaints";
    }

    @PostMapping("/actionupdate")
    public String solveProblem(@RequestParam int id,@RequestParam String status,Model model)
    {
        RiseComplaintDto riseComplaintDto=riseComplaintService.searchById(id);
        ComplaintHistory complaintHistory=new ComplaintHistory();
        List<RiseComplaintDto> riseComplaintDtos=adminService.findAllComplaints();
        if(riseComplaintDto!=null)
        {
            riseComplaintDto.setStatus(status);
            riseComplaintService.updateComplaint(riseComplaintDto);
            complaintHistory.setCid(id);
            complaintHistory.setUid(riseComplaintDto.getUserId());
            complaintHistory.setDid(riseComplaintDto.getDeptId());
            complaintHistory.setStatus(status);
            adminService.saveHistory(complaintHistory);
            model.addAttribute("list",riseComplaintDtos);
            return "EmployeeViewComplaints";
        }
        return "EmployeeViewComplaints";
    }
}

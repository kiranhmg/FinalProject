package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.*;
import com.xworkz.finalproject.model.service.AdminService;
import com.xworkz.finalproject.model.service.AdminViewComplaintService;
import com.xworkz.finalproject.model.service.DepartmentAdminService;
import com.xworkz.finalproject.model.service.RiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private RiseComplaintService riseComplaintService;
    @Autowired
    private DepartmentAdminService departmentAdminService;
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
    public String viewComplaints(@RequestParam String issueType, @RequestParam String area, Model model, HttpServletRequest request)
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


@PostMapping("/assigndepartment")
    public String assignDepartment(@RequestParam String assign,@RequestParam int id,Model model)
{
    RiseComplaintDto riseComplaintDto=riseComplaintService.searchById(id);
    DepartmentDto departmentDto=adminService.findDepartment(assign);
    ComplaintHistory complaintHistory=new ComplaintHistory();
    List<RiseComplaintDto> riseComplaintDtos=adminService.findAllComplaints();
    if(departmentDto!=null) {

        riseComplaintDto.setDeptId(departmentDto.getId());
        riseComplaintService.updateComplaint(riseComplaintDto);
        complaintHistory.setCid(id);
        complaintHistory.setUid(riseComplaintDto.getUserId());
        complaintHistory.setDid(departmentDto.getId());
        complaintHistory.setStatus(riseComplaintDto.getStatus());
        adminService.saveHistory(complaintHistory);
        model.addAttribute("list",riseComplaintDtos);
        model.addAttribute("msg","complaint assign to department successfully");

        return "AdminViewUserComplaint";
    }
    else {
        model.addAttribute("msg","department not found");
    }

    return "redirect:/AdminViewUserComplaint";
}

@PostMapping("/action")
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
    return "AdminViewUserComplaint";
}
    return "AdminViewUserComplaint";
}
    @GetMapping("/adddepartmentadmin")
    public String findDepartment(Model model)
    {
        List<DepartmentDto> list=departmentAdminService.departmrntList();
        if(!list.isEmpty())
        {
            System.err.println(list);
            model.addAttribute("list",list);
            return "AddDepartmentAdmin";
        }
        else
        {
            return "AddDepartmentAdmin";
        }
    }
@PostMapping("/addadminofdepartment")
    public String addDepartmentsAdmins(AddDepartmentAdminDto adminDto,Model model)
{
    List<DepartmentDto> departmentDtos=departmentAdminService.departmrntList();
    boolean result=adminService.saveDepartmentAdmins(adminDto);
    if (result)
    {
        model.addAttribute("list",departmentDtos);
        System.out.println("saved success fully");
        return "AddDepartmentAdmin";
    }
    else {
        return "AddDepartmentAdmin";
    }

}
}


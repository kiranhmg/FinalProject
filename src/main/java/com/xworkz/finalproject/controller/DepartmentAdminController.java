package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.*;
import com.xworkz.finalproject.model.service.DepartmentAdminService;
import com.xworkz.finalproject.model.service.RiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class DepartmentAdminController {
@Autowired
private DepartmentAdminService departmentAdminService;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private RiseComplaintService riseComplaintService;
@PostMapping("/departmentadminlogin")
    public String departmentLogIn(DepartmentAdminDto departmentAdminDto, Model model,@RequestParam String email,@RequestParam String password,HttpSession session)
{
    AddDepartmentAdminDto dto=departmentAdminService.findByEmail(email);
    DepartmentDto departmentDto=departmentAdminService.findById(dto.getDeptId());
    List<EmployeeDto> list=departmentAdminService.findEmpByDeptId(departmentDto.getId());
    session.setAttribute("dto",departmentDto);
    session.setAttribute("emplist",list);

    if(passwordEncoder.matches(password,dto.getPassword()))
    {
        System.out.println("department admin login success");
        return "Departmentadminloginsuccess";
    }
    else {
        System.out.println("department admin login failed");
        return "Departmentadminlogin";
    }
}
@GetMapping("/DepartmentAdminViewComplaints")
public String departmentViewComplaint(Model model,HttpSession session)
{
 DepartmentDto departmentDto=(DepartmentDto)  session.getAttribute("dto");
   List<RiseComplaintDto> riseComplaintDtos=departmentAdminService.findByDeptId(departmentDto.getId());
   if (!riseComplaintDtos.isEmpty())
   {
       model.addAttribute("list",riseComplaintDtos);
       return "DepartmentAdminViewComplaint";
   }
   else {
       model.addAttribute("msg","data not found");
       return "DepartmentAdminViewComplaint";
   }
}

@GetMapping("/addEmployee")
public String findDepartment(Model model, HttpSession session)
{
    List<DepartmentDto> list=departmentAdminService.departmrntList();
    if(!list.isEmpty())
    {
        System.err.println(list);
        model.addAttribute("list",list);

        return "EmployeeRegister";
    }
    else
    {
        return "EmployeeRegister";
    }
}

@PostMapping("/addemployee")
    public String addEmployee(EmployeeDto employeeDto,Model model)
{
boolean result=departmentAdminService.saveEmployee(employeeDto);
if (result)
{
    System.out.println("employee registered success fully");
    return "EmployeeRegister";
}
else {
    System.out.println("employee not registered");
    return "EmployeeRegister";
}
}
@PostMapping("/assigndepartmentemployee")
    public String updateEmpId(@RequestParam int assign,@RequestParam int id)
{
    RiseComplaintDto riseComplaintDto=riseComplaintService.searchById(id);
    if (riseComplaintDto!=null)
    {
        riseComplaintDto.setEmpId(assign);
        riseComplaintService.updateComplaint(riseComplaintDto);
        return "DepartmentAdminViewComplaint";
    }
    else {
        return "DepartmentAdminViewComplaint";
    }
}
}

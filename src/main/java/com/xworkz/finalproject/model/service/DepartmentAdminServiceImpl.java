package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.*;
import com.xworkz.finalproject.model.repo.DepartmentAdminRepo;
import com.xworkz.finalproject.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentAdminServiceImpl implements DepartmentAdminService{
    @Autowired
    private DepartmentAdminRepo departmentAdminRepo;
    @Autowired
   private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void sendEmail(EmployeeDto employeeDto){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(employeeDto.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear "+employeeDto.getName()+" "+
                " your signup success Please SignIn through this password :"+employeeDto.getPassword()+"\n\n"+
                "Thanks and Regards,\n"+" "+"X-workz Team");
        mailSender.send(simpleMailMessage);
    }
    @Override
    public AddDepartmentAdminDto findByEmail(String email) {
        AddDepartmentAdminDto departmentAdminDto=departmentAdminRepo.findByEmail(email);
        if(departmentAdminDto!=null)
        {
            System.out.println("department login success");
            return departmentAdminDto;
        }
        else { System.out.println("department login failed");
            return null;

        }
    }

    @Override
    public List<RiseComplaintDto> findByIssue(String issue) {
        List<RiseComplaintDto> list=departmentAdminRepo.findByIssue(issue);
        if(!list.isEmpty())
        {
            System.out.println("list found");
            return list;
        }
        else {
            System.out.println("list not found");
            return Collections.emptyList();
        }
    }

    @Override
    public boolean saveEmployee(EmployeeDto employeeDto) {
        EmployeeDto employeeDto1=departmentAdminRepo.findByEmailOrPhone(employeeDto);
        DepartmentDto dto=departmentAdminRepo.findByIssue1(employeeDto.getIssue());
//        System.err.println(dto);
        System.err.println(employeeDto.getIssue());
        if(employeeDto1!=null)
        {
            System.out.println("email or phone exists");
            return false;
        }
        else {
            String password= PasswordGenerator.generatePassword();
            employeeDto.setPassword(password);
           employeeDto.setDeptId(dto.getId());
           sendEmail(employeeDto);
         boolean result=  departmentAdminRepo.saveEmployee(employeeDto);
         if(result)
         {
             return true;
         }
         else {
             return false;
         }

        }


    }

    @Override
    public List<DepartmentDto> departmrntList() {
        List<DepartmentDto> list=departmentAdminRepo.departmrntList();
        if(!list.isEmpty())
        {
            return list;
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public DepartmentDto findById(int id) {
        DepartmentDto departmentDto=departmentAdminRepo.findById(id);
        if(departmentDto!=null)
        {
            return departmentDto;
        }
        else {
            return null;
        }
    }

    @Override
    public List<RiseComplaintDto> findByDeptId(int id) {
        List<RiseComplaintDto> list=departmentAdminRepo.findByDeptId(id);
        if(!list.isEmpty())
        {
            return list;
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<EmployeeDto> findEmpByDeptId(int id) {
        List<EmployeeDto> list=departmentAdminRepo.findEmpByDeptId(id);
        if (!list.isEmpty())
        {
            return list;
        }
        else {
            return Collections.emptyList();
        }
    }


}

package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.model.repo.AdminViewComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AdminViewComplaintServiceImpl implements AdminViewComplaintService{
    @Autowired
    private AdminViewComplaintRepo adminViewComplaintRepo;
    @Override
    public List<RiseComplaintDto> findByIssueAndArea(String issue, String area) {
        List<RiseComplaintDto> list=adminViewComplaintRepo.findByIssueAndArea(issue, area);
        if(!list.isEmpty())
        {
            System.out.println("list found");
            return list;
        }
      else {
          return Collections.emptyList();
        }
    }

    @Override
    public List<RiseComplaintDto> findByIssueOrArea(String issue, String area) {
        List<RiseComplaintDto> list=adminViewComplaintRepo.findByIssueOrArea(issue, area);
        if(!list.isEmpty())
        {
            System.out.println("list found");
            return list;
        }
        else {
            return Collections.emptyList();
        }
    }
}

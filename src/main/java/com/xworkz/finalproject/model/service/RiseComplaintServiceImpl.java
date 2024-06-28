package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.RiseComplaintDto;
import com.xworkz.finalproject.model.repo.RiseComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RiseComplaintServiceImpl implements RiseComplaintService{
    @Autowired
    private RiseComplaintRepo riseComplaintRepo;
    @Override
    public Optional<RiseComplaintDto> saveComplaint(RiseComplaintDto riseComplaintDto) {
       Optional<RiseComplaintDto> optional=riseComplaintRepo.saveComplaint(riseComplaintDto);
       if (optional.isPresent())
       {
           System.out.println("data is valid");
           return Optional.ofNullable(riseComplaintDto);
       }
       else {
           System.out.println("data is not valid");
           return Optional.empty();
       }
    }

    @Override
    public List<RiseComplaintDto> searchByUid(int uid) {
        List<RiseComplaintDto> list=riseComplaintRepo.searchByUid(uid);
        if (!list.isEmpty())
        {
            System.out.println("list found");
            return list;
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public RiseComplaintDto searchById(int id) {
        RiseComplaintDto riseComplaintDto=riseComplaintRepo.searchById(id);
        if (riseComplaintDto!=null)
        {
            return riseComplaintDto;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean updateComplaint(RiseComplaintDto riseComplaintDto) {
        boolean result= riseComplaintRepo.updateComplaint(riseComplaintDto);
        if(result)
        {
            System.out.println("updated success fully");
            return result;
        }
        else {
            System.out.println("not updated");
            return false;
        }
    }
}

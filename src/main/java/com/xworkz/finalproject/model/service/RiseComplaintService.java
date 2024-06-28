package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.RiseComplaintDto;

import java.util.List;
import java.util.Optional;

public interface RiseComplaintService {
    Optional<RiseComplaintDto> saveComplaint(RiseComplaintDto riseComplaintDto);
    List<RiseComplaintDto> searchByUid(int uid);
    RiseComplaintDto searchById(int id);
    boolean updateComplaint(RiseComplaintDto riseComplaintDto);


}

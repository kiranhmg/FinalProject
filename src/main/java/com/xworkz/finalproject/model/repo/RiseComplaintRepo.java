package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.RiseComplaintDto;

import java.util.List;
import java.util.Optional;

public interface RiseComplaintRepo {
    Optional<RiseComplaintDto> saveComplaint(RiseComplaintDto riseComplaintDto);
    List<RiseComplaintDto> searchByUid(int uid);
}

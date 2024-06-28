package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.RiseComplaintDto;

import java.util.List;

public interface AdminViewComplaintRepo {
    List<RiseComplaintDto> findByIssueAndArea(String issue,String area);
    List<RiseComplaintDto> findByIssueOrArea(String issue,String area);
}

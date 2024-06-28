package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.RiseComplaintDto;

import java.util.List;

public interface AdminViewComplaintService {
    List<RiseComplaintDto> findByIssueAndArea(String issue, String area);
    List<RiseComplaintDto> findByIssueOrArea(String issue,String area);

}

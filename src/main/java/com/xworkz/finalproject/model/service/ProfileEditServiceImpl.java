package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.repo.ProfileEditRepo;
import com.xworkz.finalproject.model.repo.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileEditServiceImpl implements ProfileEditService{
    @Autowired
    private ProfileEditRepo profileEditRepo;
    @Autowired
    private SignUpRepo signUpRepo;
    @Override
    public SignUpDto updateProfileDetails(SignUpDto signUpDto) {
   SignUpDto signUpDto1=profileEditRepo.updateProfileDetails(signUpDto);
   if(signUpDto1!=null)
   {
       System.out.println("profile updated success fully");
       return signUpDto1;
   }
   else {
       System.out.println("profile not updated");
       return null;
   }

    }
}

package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.ImageUploadDto;
import com.xworkz.finalproject.model.repo.ImageUploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageUploadServiceImpl implements ImageUploadService{
    @Autowired
    private ImageUploadRepo imageUploadRepo;
    @Override
    public void saveImageDetails(ImageUploadDto imageDTO) {
        imageUploadRepo.saveImageDetails(imageDTO);

    }

    @Override
    public Optional<List<ImageUploadDto>> findByUserId(Integer userId) {
        return imageUploadRepo.findByUserId(userId);

    }

    @Override
    public void updateImageDetails(ImageUploadDto imageDTO) {
        imageUploadRepo.updateImageDetails(imageDTO);

    }

    @Override
    public boolean updateStatus(int userId) {
        boolean result=imageUploadRepo.updateStatus(userId);
        if(result)
        {
            System.out.println("status updated");
            return result;
        }
        else {
            System.out.println("status not updated");
            return false;
        }
    }
}

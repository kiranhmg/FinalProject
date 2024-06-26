package com.xworkz.finalproject.model.service;

import com.xworkz.finalproject.dto.ImageUploadDto;
import com.xworkz.finalproject.model.repo.ImageUploadRepo;

import java.util.List;
import java.util.Optional;

public interface ImageUploadService {
    void saveImageDetails(ImageUploadDto imageDTO);
    Optional<List<ImageUploadDto>> findByUserId(Integer userId);
    void updateImageDetails(ImageUploadDto imageDTO);
    boolean updateStatus(int userId);

}

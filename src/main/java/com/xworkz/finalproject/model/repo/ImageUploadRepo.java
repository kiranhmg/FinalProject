package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.ImageUploadDto;

import java.util.List;
import java.util.Optional;

public interface ImageUploadRepo {
    void saveImageDetails(ImageUploadDto imageDTO);
    Optional<List<ImageUploadDto>> findByUserId(Integer userId);
    void updateImageDetails(ImageUploadDto imageDTO);
    boolean updateStatus(int userId);
    Optional<ImageUploadDto> findByUser(ImageUploadDto imageUploadDto);


}

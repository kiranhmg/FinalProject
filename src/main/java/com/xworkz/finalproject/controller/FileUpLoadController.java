package com.xworkz.finalproject.controller;

import com.xworkz.finalproject.dto.ImageUploadDto;
import com.xworkz.finalproject.dto.ProfileEditDto;
import com.xworkz.finalproject.dto.SignUpDto;
import com.xworkz.finalproject.model.service.ImageUploadService;
import com.xworkz.finalproject.model.service.ProfileEditService;
import com.xworkz.finalproject.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class FileUpLoadController {
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private ProfileEditService profileEditService;
    private static final String UPLOAD_DIR = "C:\\Users\\kiran\\OneDrive\\Desktop\\ImageUpload";

    @PostMapping("/editProfile")
    public String uploadFile(ProfileEditDto profileEditDto, @RequestParam("fileUpload") MultipartFile multipartFile, Model model,HttpSession session)
    {
        if (multipartFile.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "ProfileEdit";
        }

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String newFilename = profileEditDto.getEmail()+ "_" + originalFilename;
            Path path = Paths.get(UPLOAD_DIR, newFilename);
            Files.write(path, multipartFile.getBytes());
            model.addAttribute("message", "Profile updated successfully!");
         SignUpDto signUpDto=signUpService.searchByEmail1(profileEditDto.getEmail());
         if(signUpDto==null)
         {
             System.out.println("user not found");
             return "ProfileEdit";
         }
            Optional<List<ImageUploadDto>> existingImageOptional =imageUploadService.findByUserId(signUpDto.getId());
         ImageUploadDto imageUploadDto=new ImageUploadDto();
         if (existingImageOptional.isPresent())
         {
             for (ImageUploadDto img: existingImageOptional.get()
                  ) {
                 imageUploadService.updateStatus(img.getUserId());
             }
             imageUploadDto.setName(newFilename);
             imageUploadDto.setImageType(multipartFile.getContentType());
             imageUploadDto.setSize(multipartFile.getSize());
             imageUploadDto.setUserId(signUpDto.getId());
             imageUploadDto.setCreatedAt(LocalDateTime.now());
             imageUploadDto.setCreatedBy(signUpDto.getEmail());
             imageUploadDto.setStatus("active");
             imageUploadService.saveImageDetails(imageUploadDto);
             Optional<List<ImageUploadDto>> existingImageOptional1 =imageUploadService.findByUserId(imageUploadDto.getUserId());
             if(existingImageOptional1.isPresent())
             {
                 for (ImageUploadDto img:existingImageOptional1.get()
                 ) {
                     if(img.getStatus().equals("active")) {
                         session.setAttribute("profilefind", "/profile/" + img.getName());
                     }
                     else {
                         session.setAttribute("profilefind", "/profile/" +"profile1");
                     }
                 }
             }
         }
         else {
//             imageUploadDto = new ImageUploadDto();
             // Update existing image details
             imageUploadDto.setName(newFilename);
             imageUploadDto.setImageType(multipartFile.getContentType());
             imageUploadDto.setSize(multipartFile.getSize());
             imageUploadDto.setUserId(signUpDto.getId());
             imageUploadDto.setCreatedAt(LocalDateTime.now());
             imageUploadDto.setCreatedBy(signUpDto.getEmail());
             imageUploadDto.setStatus("active");
             imageUploadService.saveImageDetails(imageUploadDto);
             Optional<List<ImageUploadDto>> existingImageOptional1 =imageUploadService.findByUserId(imageUploadDto.getUserId());
             if(existingImageOptional1.isPresent())
             {
                 for (ImageUploadDto img:existingImageOptional1.get()
                 ) {
                     if(img.getStatus().equals("active")) {
                         session.setAttribute("profilefind", "/profile/" + img.getName());
                     }
                     else {
                         session.setAttribute("profilefind", "/profile/" +"profile1");
                     }
                 }
             }
         }


         signUpDto.setFirstName(profileEditDto.getFirstName());
         signUpDto.setLastName(profileEditDto.getLastName());
         signUpDto.setPhone(profileEditDto.getPhone());
         if(signUpDto!=null)
         {
             profileEditService.updateProfileDetails(signUpDto);
             System.out.println("updated***********");
             model.addAttribute("msg","profile updated success fully");
         }
         else {
             System.out.println("not updated");
             model.addAttribute("msg","profile updation failed");
         }

//         session.setAttribute("profilefind","/profile/"+imageUploadDto.getName());

        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }

        return "ProfileEdit";

    }

}

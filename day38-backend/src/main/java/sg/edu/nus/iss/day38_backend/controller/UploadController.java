package sg.edu.nus.iss.day38_backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.day38_backend.service.ImageService;

@Controller
@RequestMapping
public class UploadController {
    
    @Autowired
    ImageService imgSvc;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postFileUpload(@RequestPart MultipartFile myfile, @RequestPart String comments) throws IOException {

        ModelAndView mav = new ModelAndView("picturefile");

        System.out.println("myfile >>> " + myfile.getInputStream().toString());
        System.out.println("myfile >>> " + myfile.getOriginalFilename());
        System.out.println("myfile >>> " + myfile.getSize());

        imgSvc.saveToDB(myfile.getInputStream(), comments);

        mav.addObject("comments", comments);

        return mav;
    }
}

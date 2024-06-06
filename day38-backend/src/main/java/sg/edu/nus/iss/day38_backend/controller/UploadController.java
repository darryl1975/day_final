package sg.edu.nus.iss.day38_backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import sg.edu.nus.iss.day38_backend.exceptions.ResponseMessage;
import sg.edu.nus.iss.day38_backend.model.FileInfo;
import sg.edu.nus.iss.day38_backend.service.ImageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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


    @PostMapping(path = "/uploads3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postFileUploadS3(@RequestPart MultipartFile myfile, @RequestPart String comments) throws IOException {

        ModelAndView mav = new ModelAndView("picturefile");

        System.out.println("myfile >>> " + myfile.getInputStream().toString());
        System.out.println("myfile >>> " + myfile.getOriginalFilename());
        System.out.println("myfile >>> " + myfile.getSize());

        String resultId = imgSvc.saveToS3(myfile);

        // References: https://day39.sgp1.digitaloceanspaces.com/4634b174
        mav.addObject("imageURL", "https://day39.sgp1.digitaloceanspaces.com/" + resultId);
        mav.addObject("comments", comments);

        return mav;
    }

    // additonal:

    @PostMapping("/file-upload")
    public ResponseEntity<ResponseMessage> uploadFileToDirectory(@RequestParam("file") MultipartFile file, @RequestParam("comments") String comments) {

        try {
            // imgSvc.save(file);
            String resultId = imgSvc.saveToS3(file);

            String message = "File uploaded successfully";

            System.out.println("Multiplartfile>>>" + file.toString());
            System.out.println("Comments>>>" + comments);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception ex) {

            String message = ex.toString();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    
}

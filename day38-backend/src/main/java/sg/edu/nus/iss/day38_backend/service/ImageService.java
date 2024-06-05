package sg.edu.nus.iss.day38_backend.service;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day38_backend.repository.ImageRepo;

@Service
public class ImageService {
    
    @Autowired
    ImageRepo imgRepo;

    public String saveToDB(InputStream is, String contentType) {
        String pic_id = UUID.randomUUID().toString().substring(0, 8);
        imgRepo.save(pic_id, is, contentType);

        return pic_id;
    }

}

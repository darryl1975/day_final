package sg.edu.nus.iss.day38_backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    // Additional: upload files and save to a local directory "uploads" within the
    // application

    // hardcode the path to create
    public final Path fileUpload = Paths.get("uploads");

    public void initDirectory() {
        try {
            Files.createDirectories(fileUpload);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.fileUpload.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Stream<Path> loadAll() throws IOException {
        return Files.walk(this.fileUpload, 1).filter(path -> !path.equals(this.fileUpload)).map(this.fileUpload::relativize);
    }
}

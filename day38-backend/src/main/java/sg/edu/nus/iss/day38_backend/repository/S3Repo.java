package sg.edu.nus.iss.day38_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class S3Repo {

    @Autowired
    private AmazonS3 s3;

    public String saveToS3(MultipartFile uploadFile) {

        try {
            Map<String, String> userData = new HashMap<>();
            userData.put("name", "Vivien");
            userData.put("filename", uploadFile.getOriginalFilename());

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(uploadFile.getContentType());
            metadata.setContentLength(uploadFile.getSize());
            metadata.setUserMetadata(userData);

            String id = UUID.randomUUID().toString().substring(0, 8);

            PutObjectRequest putRequest = new PutObjectRequest("day39", "%s".formatted(id), uploadFile.getInputStream(),
                    metadata);
            putRequest.withCannedAcl(CannedAccessControlList.PublicRead);

            PutObjectResult result = s3.putObject(putRequest);
            System.out.println("PutObjectResult: " + result.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}

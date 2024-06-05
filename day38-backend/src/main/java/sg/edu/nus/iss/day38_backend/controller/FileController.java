package sg.edu.nus.iss.day38_backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import sg.edu.nus.iss.day38_backend.model.FileInfo;
import sg.edu.nus.iss.day38_backend.model.ImageData;
import sg.edu.nus.iss.day38_backend.service.ImageService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class FileController {

    @Autowired
    ImageService imgSvc;

    @Autowired
    private AmazonS3 s3;

    @GetMapping("/list-files")
    public ResponseEntity<List<FileInfo>> getFilesList() throws IOException {
        List<FileInfo> fileInfos = imgSvc.loadAll().map(path -> {

            // do something here
            String filename = path.getFileName().toString();
            String fileURL = MvcUriComponentsBuilder
                    .fromMethodName(UploadController.class, "getFileByFilename", path.getFileName().toString()).build()
                    .toString();

            return new FileInfo(filename, fileURL);

        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<Resource> getFileByFilename(@PathVariable String filename) {
        Resource resource = imgSvc.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment, filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/myfile/{pic-id}")
    public ResponseEntity<ImageData> getImageDataByPictureId(@PathVariable("pic-id") String pic_id) {

        Optional<ImageData> resultImageData = imgSvc.getPicById(pic_id);

        if (resultImageData.isPresent()) {
            return ResponseEntity.ok().body(resultImageData.get());
        } else {
            return ResponseEntity.ok().body(null);
        }
    }

    @GetMapping("/s3file/{file-id}")
    public ResponseEntity<byte[]> GetFileFromS3(@PathVariable("file-id") String fileId) {
        GetObjectRequest getRequest = new GetObjectRequest("day39", fileId);
        S3Object result = s3.getObject(getRequest);

        ObjectMetadata metadata = result.getObjectMetadata();
        Map<String, String> userData = metadata.getUserMetadata();

        byte[] buffer = null;
        try (S3ObjectInputStream is = result.getObjectContent()) {
            buffer = is.readAllBytes();

        } catch (AmazonS3Exception ex) {

        } catch (Exception ex) {

        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buffer);

    }

}

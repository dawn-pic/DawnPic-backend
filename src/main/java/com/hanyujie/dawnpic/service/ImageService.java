package com.hanyujie.dawnpic.service;

import com.hanyujie.dawnpic.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hanyujie.dawnpic.entity.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Autowired
    ImageMapper imageMapper;

    public String uploadImage(MultipartFile rFile) throws IOException {
        byte[] fileBytes = rFile.getBytes();

        String name = "defaultName";

        Image image = new Image(name);

        imageMapper.insertPic(image);

        Integer imageId = image.getId();

        String userHome = System.getProperty("user.home");

        Path filePath = Paths.get(userHome, "Pictures", imageId.toString() + ".jpg");

        try (FileOutputStream fos = new FileOutputStream(filePath.toString())) {
            fos.write(fileBytes);
            System.out.println(fileBytes.length);
            System.out.println("Data written/rewritten to file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        };

        return ""+imageId;
    }

    public ResponseEntity<Resource> downloadImage(Integer imageId) throws IOException{
        String userHome = System.getProperty("user.home");

        Path filePath = Paths.get(userHome, "Pictures", imageId.toString() + ".jpg");

        File file = new File(filePath.toString());
        if (!file.exists()) {
            throw new IOException("File doesn't exist");
        }

        FileSystemResource resource = new FileSystemResource(filePath);

        MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);

        ContentDisposition disposition = ContentDisposition.inline().filename(resource.getFilename()).build();

        headers.setContentDisposition(disposition);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}

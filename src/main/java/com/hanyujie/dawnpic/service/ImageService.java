package com.hanyujie.dawnpic.service;

import com.hanyujie.dawnpic.entity.Image;
import com.hanyujie.dawnpic.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    private final ImageMapper imageMapper;

    @Autowired
    public ImageService(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    final static String USER_HOME = System.getProperty("user.home");

    public String uploadImage(MultipartFile rFile) throws IOException {
        String fileName = rFile.getOriginalFilename();
        String fileExtension = fileName != null ? fileName.substring(fileName.lastIndexOf(".")+1) : null;

        byte[] bytes = rFile.getBytes();
        Long uploadDate = System.currentTimeMillis();

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        Image image = new Image(0, fileName, fileExtension, null, null, uploadDate, width, height);

        imageMapper.insertPic(image);

        Integer imageId = image.getId();

        Path filePath = Paths.get(USER_HOME, "Pictures", imageId + "." + fileExtension);

        try (FileOutputStream fos = new FileOutputStream(filePath.toString())) {
            fos.write(bytes);
            System.out.println("Data written/rewritten to file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ""+imageId;
    }

    public ResponseEntity<Resource> downloadImage(Integer imageId) throws IOException{
        Image image = imageMapper.getPic(imageId);

        Path filePath = Paths.get(USER_HOME, "Pictures", imageId.toString() + "." + image.getExtension());

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

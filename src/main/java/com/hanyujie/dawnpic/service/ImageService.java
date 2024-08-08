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
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageMapper imageMapper;

    @Autowired
    public ImageService(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    private static final String USER_HOME = System.getProperty("user.home");

    /**
     * Uploads an image file and saves its metadata to the database.
     * @param rFile the image file to upload
     * @return the UUID of the uploaded image
     * @throws IOException if an I/O error occurs
     */
    public String uploadImage(MultipartFile rFile) throws InvalidImageExtensionException, IOException {
        String fileName = rFile.getOriginalFilename();

        // get the image extension and judge if it is valid
        String imageExtension = ImageExtensionChecker.getFileExtension(fileName);
        Boolean isImageExtension = ImageExtensionChecker.isImageExtension(imageExtension);
        if (!isImageExtension) {
            throw new InvalidImageExtensionException("invalid image extension: " + imageExtension);
        }

        byte[] bytes = rFile.getBytes();
        long uploadDate = System.currentTimeMillis();

        // Read image dimensions
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        // Create a unique image UUID
        UUID imageUuid = UUID.randomUUID();

        // Create and save image metadata
        // including new created UUID
        Image image = new Image(imageUuid, fileName, imageExtension, null, null, uploadDate, width, height);
        imageMapper.insert(image);

        // Save image file to disk
        Path filePath = Paths.get(USER_HOME, "Pictures", imageUuid + "." + imageExtension);
        try (FileOutputStream fos = new FileOutputStream(filePath.toString())) {
            fos.write(bytes);
        }

        return imageUuid.toString();
    }

    /**
     * Downloads an image file by its ID.
     * @param imageUuid the UUID of the image to download
     * @return the image file as a ResponseEntity
     * @throws FileNotFoundException if the file does not exist
     */
    public ResponseEntity<Resource> downloadImage(UUID imageUuid) throws FileNotFoundException {
//        System.out.println(imageUuid);
        Image image = imageMapper.selectById(imageUuid);
        Path filePath = Paths.get(USER_HOME, "Pictures", imageUuid + "." + image.getExtension());

        File file = new File(filePath.toString());
        if (!file.exists()) {
            throw new FileNotFoundException("File doesn't exist");
        }

        FileSystemResource resource = new FileSystemResource(filePath);
        MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDisposition(ContentDisposition.inline().filename(image.getName()).build());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}

package com.hanyujie.dawnpic.service;

import com.hanyujie.dawnpic.entity.Image;
import com.hanyujie.dawnpic.entity.User;
import com.hanyujie.dawnpic.entity.UserImage;
import com.hanyujie.dawnpic.mapper.ImageMapper;
import com.hanyujie.dawnpic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageMapper imageMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public ImageService(ImageMapper imageMapper, UserService userService, UserMapper userMapper) {
        this.imageMapper = imageMapper;
        this.userService = userService;
        this.userMapper = userMapper;
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

        // Get user id from Spring security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            userId = 0L;
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.getUserFromUsername(userDetails.getUsername());

            userId = user.getId();
        }

        // Create and save image metadata
        // including new created UUID
        Image image = new Image(imageUuid, fileName, imageExtension, null, null, uploadDate, width, height, userId);
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

    public List<UserImage> getUserImage(UserDetails userDetails) {
        String username = null;

        if (userDetails == null) {
            username = "anonymousUser";
        } else {
            username = userDetails.getUsername();
        }

        return userMapper.selectUserImageByUsername(username);
    }
}

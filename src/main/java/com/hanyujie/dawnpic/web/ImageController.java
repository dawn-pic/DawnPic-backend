package com.hanyujie.dawnpic.web;

import com.hanyujie.dawnpic.entity.ImageUploadResponse;
import com.hanyujie.dawnpic.entity.UserImageCountResponse;
import com.hanyujie.dawnpic.service.ImageService;
import com.hanyujie.dawnpic.service.InvalidImageExtensionException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("files") List<MultipartFile> files) throws IOException {
        try {
            List<String> results = new ArrayList<>();
            for (MultipartFile file : files) {
                String result = imageService.uploadImage(file);
                results.add(result);
            }
            ImageUploadResponse imageUploadResponse = new ImageUploadResponse(results);
            return ResponseEntity.status(HttpStatus.OK).body(imageUploadResponse);
        } catch (InvalidImageExtensionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/image/{imageUuid}")
    public ResponseEntity<?> getImage(@PathVariable UUID imageUuid, @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch, HttpServletResponse httpServletResponse) {
        try {
            String eTag = "\"" + imageUuid.toString() + "\"";

            if (ifNoneMatch != null && imageService.ifImageExist(imageUuid)) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }

            httpServletResponse.setHeader("ETag", eTag);
            httpServletResponse.setHeader("Cache-Control", "max-age=31536000, immutable");
            return imageService.downloadImage(imageUuid);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }

    @GetMapping("/api/userImages")
    public ResponseEntity<?> getUserImages(@RequestParam int page ,@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(imageService.getUserImage(userDetails, page));
    }

    @GetMapping("/api/userImagesCount")
    public ResponseEntity<?> getUserImagesCount(@AuthenticationPrincipal UserDetails userDetails) {
        int imageCount = imageService.getUserImageCount(userDetails);
        UserImageCountResponse userImageCountResponse = new UserImageCountResponse(imageCount);
        return ResponseEntity.ok(userImageCountResponse);
    }

    @DeleteMapping("/api/image/{imageUuid}")
    public ResponseEntity<?> deleteImage(@PathVariable UUID imageUuid, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String username = userDetails.getUsername();
            if (imageService.isOwner(imageUuid, username)) {
                imageService.deleteImage(imageUuid);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this image");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }
}

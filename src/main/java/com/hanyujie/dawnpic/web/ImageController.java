package com.hanyujie.dawnpic.web;

import com.hanyujie.dawnpic.service.ImageService;
import com.hanyujie.dawnpic.service.InvalidImageExtensionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String result = imageService.uploadImage(file);
            return ResponseEntity.ok(result);
        } catch (InvalidImageExtensionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/api/image/{imageUuid}")
    public ResponseEntity<?> getImage(@PathVariable UUID imageUuid) {
        try {
            return imageService.downloadImage(imageUuid);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }
}

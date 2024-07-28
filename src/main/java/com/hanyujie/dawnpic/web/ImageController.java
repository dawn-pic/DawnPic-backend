package com.hanyujie.dawnpic.web;

import com.hanyujie.dawnpic.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String result = imageService.uploadImage(file);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/image/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable Integer imageId) {
        try {
            return imageService.downloadImage(imageId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }
}

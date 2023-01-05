package com.test.project.imageuploader.controller;

import com.test.project.imageuploader.dto.ApiResponse;
import com.test.project.imageuploader.dto.FetchImageResponse;
import com.test.project.imageuploader.dto.ImageUploadRequest;
import com.test.project.imageuploader.dto.ImageUploadResponse;
import com.test.project.imageuploader.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/image")
    public ResponseEntity<ApiResponse<ImageUploadResponse>> uploadImage(@RequestParam("file") MultipartFile multipartFile,
                                                                        @RequestParam("userId") String userId) {
        log.info("Request to upload an image");
        ImageUploadRequest imageUploadRequest = new ImageUploadRequest(multipartFile, userId);
        ApiResponse<ImageUploadResponse> apiResponse = ApiResponse.
                withData(imageService.upload(imageUploadRequest))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/image")
    public ResponseEntity<ApiResponse<FetchImageResponse>> fetchImages(@RequestParam String userId) {
        log.info("Request to fetch images for user: " + userId);
        ApiResponse<FetchImageResponse> apiResponse = ApiResponse.
                withData(imageService.fetchImages(userId))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<ApiResponse<FetchImageResponse>> fetchImage(@PathVariable String imageId) {
        log.info("Request to fetch imager with id: " + imageId);
        ApiResponse<FetchImageResponse> apiResponse = ApiResponse.
                withData(imageService.fetchImage(imageId))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/image/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable String imageId) {
        log.info("Request to fetch image with id: " + imageId);
        imageService.deleteImage(imageId);
        return ResponseEntity.ok(ApiResponse.withNoData().setStatus("Success").setCode(1));
    }
}

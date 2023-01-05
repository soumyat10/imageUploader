package com.test.project.imageuploader.service;

import com.test.project.imageuploader.client.ImageUrClient;
import com.test.project.imageuploader.dto.FetchImageResponse;
import com.test.project.imageuploader.dto.ImageUploadRequest;
import com.test.project.imageuploader.dto.ImageUploadResponse;
import com.test.project.imageuploader.dto.UploadImageResponse;
import com.test.project.imageuploader.entity.ImageEntity;
import com.test.project.imageuploader.entity.UserEntity;
import com.test.project.imageuploader.exception.NotFoundException;
import com.test.project.imageuploader.repository.ImageRepository;
import com.test.project.imageuploader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageUrClient imageUrClient;

    public ImageUploadResponse upload(ImageUploadRequest request) {
        ImageUploadResponse response = new ImageUploadResponse();
        Optional<UserEntity> user = userRepository.findByUserIdAndEnabled(request.getUserId(), true);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        UploadImageResponse uploadImageResponse = imageUrClient.upload(request.getFile());
        ImageEntity image = new ImageEntity();
        image.setImageUrl(uploadImageResponse.getLink());
        image.setUser(user.get());
        image.setCreatedAt(new Date());
        image.setDeleteHash(uploadImageResponse.getDeletehash());
        image = imageRepository.save(image);
        response.setImageId(image.getImageId());
        response.setImageLink(uploadImageResponse.getLink());
        return response;
    }

    public FetchImageResponse fetchImages(String userId) {
            FetchImageResponse response = new FetchImageResponse();
        Optional<UserEntity> userEntityOptional = userRepository.findByUserIdAndEnabled(userId, true);
        if (userEntityOptional.isEmpty())
            throw new NotFoundException("User not found with given id:" + userId);
        List<ImageEntity> images = userEntityOptional.get().getImages();
        response.setImages(images);
        return response;
    }

    public FetchImageResponse fetchImage(String imageId) {
        FetchImageResponse response = new FetchImageResponse();
        Optional<ImageEntity> imageEntityOptional = imageRepository.findByImageId(imageId);
        if (imageEntityOptional.isEmpty())
            throw new NotFoundException("Image not found with given id:" + imageId);
        List<ImageEntity> images = new ArrayList<>();
        images.add(imageEntityOptional.get());
        response.setImages(images);
        return response;
    }

    public void deleteImage(String imageId) {
        Optional<ImageEntity> imageEntityOptional = imageRepository.findByImageId(imageId);
        if (imageEntityOptional.isEmpty())
            throw new NotFoundException("Image not found with given id:" + imageId);
        imageUrClient.deleteImage(imageEntityOptional.get().getDeleteHash());
        imageRepository.delete(imageEntityOptional.get());
    }
}

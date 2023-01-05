package com.test.project.imageuploader.repository;

import com.test.project.imageuploader.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {

    Optional<ImageEntity> findByImageId(String imageId);
}

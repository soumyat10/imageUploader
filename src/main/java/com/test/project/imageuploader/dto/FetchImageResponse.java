package com.test.project.imageuploader.dto;

import com.test.project.imageuploader.entity.ImageEntity;
import lombok.Data;

import java.util.List;

@Data
public class FetchImageResponse {
    List<ImageEntity> images;
}

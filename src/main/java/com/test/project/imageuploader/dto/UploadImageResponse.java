package com.test.project.imageuploader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UploadImageResponse {
    private String link;
    private String deletehash;
}

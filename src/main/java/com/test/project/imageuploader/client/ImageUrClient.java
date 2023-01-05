package com.test.project.imageuploader.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.imageuploader.dto.ApiResponse;
import com.test.project.imageuploader.dto.UploadImageResponse;
import com.test.project.imageuploader.exception.ServiceException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import static com.test.project.imageuploader.constant.AppConstants.*;

@Component
public class ImageUrClient {

    @Value("${image.ur.base.url}")
    private String imageUrBaseUrl;

    @Value("${image.ur.clientId}")
    private String imageUrClientId;

    public UploadImageResponse upload(MultipartFile file) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            final String imageUploadUrl = imageUrBaseUrl + "/3/upload";
            body.add(PARAM_IMAGE, Base64.encodeBase64String(file.getBytes()));
            body.add(PARAM_TYPE, PARAM_VALUE_BASE_64);
            headers.add(HEADER_AUTHORIZATION, "Client-ID " + imageUrClientId);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<ApiResponse> response = restTemplate.postForEntity(imageUploadUrl, entity, ApiResponse.class);
            ObjectMapper objectMapper = new ObjectMapper();
            UploadImageResponse uploadImageResponse = objectMapper.convertValue(response.getBody().getData(), UploadImageResponse.class);
            return uploadImageResponse;
        } catch (Exception e) {
            throw new ServiceException("Exception while calling image ur service : " + e.getMessage());
        }
    }

    public void deleteImage(String deleteHash) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            final String imageDeletionUrl = imageUrBaseUrl + "/3/image/" + deleteHash;
            headers.add(HEADER_AUTHORIZATION, "Client-ID " + imageUrClientId);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
            ResponseEntity<ApiResponse> response = restTemplate.exchange(imageDeletionUrl, HttpMethod.DELETE, entity, ApiResponse.class);
        } catch (Exception e) {
            throw new ServiceException("Exception while calling image ur service : " + e.getMessage());
        }
    }
}

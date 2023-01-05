package com.test.project.imageuploader.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String contact;
}

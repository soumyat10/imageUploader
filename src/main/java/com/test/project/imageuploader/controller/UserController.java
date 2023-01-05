package com.test.project.imageuploader.controller;

import com.test.project.imageuploader.dto.ApiResponse;
import com.test.project.imageuploader.dto.CreateUserRequest;
import com.test.project.imageuploader.dto.UpdateUserRequest;
import com.test.project.imageuploader.entity.UserEntity;
import com.test.project.imageuploader.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserEntity>> registerUser(@RequestBody CreateUserRequest request) {
        log.info("Request to register a user");
        ApiResponse<UserEntity> apiResponse = ApiResponse.
                withData(userService.createUser(request))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> deleteUser(@PathVariable String userId) {
        log.info("Request to delete user with userId: " + userId);
        ApiResponse<UserEntity> apiResponse = ApiResponse.
                withData(userService.deleteUser(userId))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/user")
    public ResponseEntity<ApiResponse<UserEntity>> updateUser(@RequestBody UpdateUserRequest request) {
        log.info("Request to update user with userId: " + request.getUserId());
        ApiResponse<UserEntity> apiResponse = ApiResponse.
                withData(userService.updateUser(request))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<UserEntity>>> fetchUsers() {
        log.info("Request to fetch all users");
        ApiResponse<List<UserEntity>> apiResponse = ApiResponse.
                withData(userService.fetchUsers())
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<UserEntity>> fetchUser(@PathVariable String userId) {
        log.info("Request to fetch all users");
        ApiResponse<UserEntity> apiResponse = ApiResponse.
                withData(userService.fetchUser(userId))
                .setStatus("Success").setCode(1);
        return ResponseEntity.ok(apiResponse);
    }
}

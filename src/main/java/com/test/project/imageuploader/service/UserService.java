package com.test.project.imageuploader.service;

import com.test.project.imageuploader.dto.CreateUserRequest;
import com.test.project.imageuploader.dto.UpdateUserRequest;
import com.test.project.imageuploader.entity.UserEntity;
import com.test.project.imageuploader.exception.NotFoundException;
import com.test.project.imageuploader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setContact(createUserRequest.getContact());
        user.setEmail(createUserRequest.getEmail());
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        return userRepository.save(user);
    }

    public UserEntity deleteUser(final String userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserIdAndEnabled(userId, true);
        if (userEntityOptional.isEmpty())
            throw new NotFoundException("User not found with given id:" + userId);
        UserEntity user = userEntityOptional.get();
        user.setEnabled(false);
        return userRepository.save(user);
    }

    public UserEntity updateUser(final UpdateUserRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserIdAndEnabled(request.getUserId(), true);
        if (userEntityOptional.isEmpty())
            throw new NotFoundException("User not found with given id:" + request.getUserId());
        UserEntity user = userEntityOptional.get();
        if (null != request.getEmail())
            user.setEmail(request.getEmail());
        if (null != request.getPassword())
            user.setPassword(request.getPassword());
        if (null != request.getContact())
            user.setContact(request.getContact());
        if (null != request.getUsername())
            user.setUsername(request.getUsername());
        return userRepository.save(user);
    }

    public List<UserEntity> fetchUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity fetchUser(String userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserIdAndEnabled(userId, true);
        if (userEntityOptional.isEmpty())
            throw new NotFoundException("User not found with given id:" + userId);
        return userEntityOptional.get();
    }
}

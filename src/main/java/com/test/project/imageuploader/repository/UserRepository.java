package com.test.project.imageuploader.repository;

import com.test.project.imageuploader.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsernameAndEnabled(String username,boolean enabled);

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByUserIdAndEnabled(String userId, boolean enabled);
}

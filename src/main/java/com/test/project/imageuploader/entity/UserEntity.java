package com.test.project.imageuploader.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.List;


@Data
@Entity
@ToString
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", length = 36)
    private String userId;

    private String username;

    private String password;

    private String email;

    private String contact;

    private boolean enabled = true;

    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ImageEntity> images;

}

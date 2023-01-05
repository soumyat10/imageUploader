package com.test.project.imageuploader.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "image_id", length = 36)
    private String imageId;

    @ToString.Exclude
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Date createdAt;

    private String imageUrl;

    private String deleteHash;

}

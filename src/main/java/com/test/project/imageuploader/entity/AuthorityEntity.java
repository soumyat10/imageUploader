package com.test.project.imageuploader.entity;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "authority_id", length = 36)
    private String id;

    private String username;

    private String authority;

    private String email;
}

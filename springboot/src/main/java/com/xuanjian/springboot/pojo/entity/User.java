package com.xuanjian.springboot.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "user_name",unique = true,nullable = false)
    private String userName;

    @Column(name = "user_password", nullable = false)
    private String UserPassword;

    @Column(name= "user_slat", nullable = false)
    private String userSalt;

    @ManyToMany
    @JoinTable(name = "upload_table", joinColumns = {@JoinColumn(name= "uploader_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name= "app_id", referencedColumnName="id")})
    private Set<App> appList = new HashSet<App>(0);
}

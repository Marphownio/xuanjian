package com.xuanjian.springboot.pojo.entity;

import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "apps")
public class App implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "package_name",nullable = false)
    private String packageName;

    @Column(name = "version_id",nullable = true)
    private Integer versionId;

    @Column(name = "version_name",nullable = true)
    private String versionName;

    @Column(name = "size",nullable = false)
    private float size;

    @Column(name = "md5",nullable = false)
    private String md5;

    @Column(name = "check_time",nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    @Column(name = "analyse_time")
    private Integer analyseTime;

    @Column(name = "signature_publisher")
    private String signaturePublisher;

    @Column(name = "signature_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date signatureTime;

    @Column(name = "restore_path",nullable = false)
    private String restorePath;

}


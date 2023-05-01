package com.xuanjian.springboot.pojo.entity;

import javax.persistence.*;

import com.xuanjian.springboot.pojo.enums.AnalysisState;
import com.xuanjian.springboot.pojo.enums.SandboxState;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "apps")
public class App implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "package_name",nullable = false)
    private String packageName;

    @Column(name = "version_id",nullable = true)
    private String versionId;

    @Column(name = "size",nullable = false)
    private float size;

    @Column(name = "md5")
    private String md5;

    @Column(name = "first_upload_time",nullable = false)
    private String firstUploadTime;

    @Column(name = "sandbox_state",nullable = false)
    private SandboxState sandboxState;

    @Column(name = "analysis_state",nullable = false)
    private AnalysisState analysisState;

    @Column(name = "analyse_time")
    private String analyseTime;

    @Column(name = "signature_publisher")
    private String signaturePublisher;

    @Column(name = "signature_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String signatureTime;

    @Column(name = "restore_path",nullable = false)
    private String restorePath;

    @Column(name = "plat_version")
    private Integer platVersion;

    @Column(name = "version_code")
    private String versionCode;

    @Column(name = "sha1")
    private String sha1;

    @Column(name = "sha256")
    private String sha256;

    @Column(name = "hash_algo")
    private String hashAlgo;

    @Column(name = "sign_algo")
    private String signAlgo;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "org_unit")
    private String orgUnit;

    @Column(name = "org")
    private String org;

    @Column(name = "locality")
    private String locality;

    @Column(name = "place")
    private String place;

    @Column(name = "country")
    private String country;

    @Column(name = "permissions")
    private String permissions;

    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "recheck_table", joinColumns = {@JoinColumn(name= "origin_app_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name= "rechecked_app_id", referencedColumnName="id")})
    private Set<App> recheckAppList = new HashSet<App>(0);
}


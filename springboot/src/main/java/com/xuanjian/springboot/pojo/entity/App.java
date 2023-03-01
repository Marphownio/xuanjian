package com.xuanjian.springboot.pojo.entity;

import javax.persistence.*;

import com.xuanjian.springboot.pojo.enums.AnalysisState;
import com.xuanjian.springboot.pojo.enums.SandboxState;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

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
    private Integer analyseTime;

    @Column(name = "signature_publisher")
    private String signaturePublisher;

    @Column(name = "signature_time")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String signatureTime;

    @Column(name = "restore_path",nullable = false)
    private String restorePath;

    @Column(name = "plat_version")
    private Integer platVersion;
}


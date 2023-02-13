package com.xuanjian.springboot.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "screenshot")
public class Screenshot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "store_path",nullable = false)
    private String storePath;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false, referencedColumnName = "id")
    private App app;
}

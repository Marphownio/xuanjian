package com.xuanjian.springboot.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name="domain")
public class Domain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Long id;

    @Column(name = "domain_name",nullable = false)
    private String domainName;

    @Column(name = "ip")
    private String ip;

    @Column(name = "cname")
    private String cname;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false, referencedColumnName="id")
    private App app;

    @ManyToMany
    @JoinTable(name = "distribution",joinColumns = {@JoinColumn(name= "distributing_domain", referencedColumnName="id")},inverseJoinColumns = {@JoinColumn(name = "distributed_domain", referencedColumnName="id")})
    private Set<Domain> distributionDomain = new HashSet<Domain>(0);

    @Column(length = 16, name = "domain_type", nullable = false)
    private int domainType;
}

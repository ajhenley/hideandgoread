package com.ajlabs.demo.model;

import javax.persistence.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Long classid;

    private String name;

    @Lob
    @Column(length=10000)
    private PublicKey publicKey;

    @Lob
    @Column(length=10000)
    private PrivateKey privateKey;

    private String headshot;

    private String email;

    private Date lastLogin;

    private String username;

    private String password;

    private String usertype;

    public Student() {
    }

    public Student(String name, PublicKey publicKey, PrivateKey privateKey, String headshot, Date lastLogin, String username, String password, String usertype, String email, Long classid) {
        this.name = name;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.headshot = headshot;
        this.lastLogin = lastLogin;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.email = email;
        this.classid = classid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }
}

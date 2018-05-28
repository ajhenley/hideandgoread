package com.ajlabs.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Emessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long fromid;
    private String fromstring;

    private Long toid;
    private String tostring;

    private String subject;
    private String body;

    private boolean isEncrypted;
    private boolean isSigned;
    private Date createDate;

    public Emessage() {
    }

    public Emessage(Long fromid, String fromstring, Long toid, String tostring, String subject, String body, boolean isEncrypted, boolean isSigned, Date createDate) {
        this.fromid = fromid;
        this.fromstring = fromstring;
        this.toid = toid;
        this.tostring = tostring;
        this.subject = subject;
        this.body = body;
        this.isEncrypted = isEncrypted;
        this.isSigned = isSigned;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromid() {
        return fromid;
    }

    public void setFromid(Long fromid) {
        this.fromid = fromid;
    }

    public String getFromstring() {
        return fromstring;
    }

    public void setFromstring(String fromstring) {
        this.fromstring = fromstring;
    }

    public Long getToid() {
        return toid;
    }

    public void setToid(Long toid) {
        this.toid = toid;
    }

    public String getTostring() {
        return tostring;
    }

    public void setTostring(String tostring) {
        this.tostring = tostring;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

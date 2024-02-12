package com.guud.company.library.master.commune.model;

import com.guud.company.library.core.COAbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_MST_COMMUNE")
public class TMstCommune extends COAbstractEntity<TMstCommune> {
    private static final long serialVersionUID = 5639161183568020457L;

    private String code;
    private String desc;
    private String descOth;
    private char recStatus;
    private Date ComDtCreate;
    private String ComUidCreate;
    private Date ComDtLupd;
    private String ComUidLupd;

    public TMstCommune() {
    }

    public TMstCommune(String code, String desc, String descOth) {
        this.code = code;
        this.desc = desc;
        this.descOth = descOth;
    }

    @Id
    @Column(name = "COM_CODE", unique = true, nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "COM_DESC", nullable = false, length = 512)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "COM_DESC_OTH", length = 512)
    public String getDescOth() {
        return descOth;
    }

    public void setDescOth(String descOth) {
        this.descOth = descOth;
    }

    @Column(name = "COM_REC_STATUS", nullable = false, length = 1)
    public char getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(char recStatus) {
        this.recStatus = recStatus;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COM_DT_CREATE", length = 19)
    public Date getComDtCreate() {
        return ComDtCreate;
    }

    public void setComDtCreate(Date comDtCreate) {
        ComDtCreate = comDtCreate;
    }
    @Column(name = "COM_UID_CREATE", length = 35)
    public String getComUidCreate() {
        return ComUidCreate;
    }

    public void setComUidCreate(String comUidCreate) {
        ComUidCreate = comUidCreate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COM_DT_LUPD", length = 19)
    public Date getComDtLupd() {
        return ComDtLupd;
    }

    public void setComDtLupd(Date comDtLupd) {
        ComDtLupd = comDtLupd;
    }
    @Column(name = "COM_UID_LUPD", length = 35)
    public String getComUidLupd() {
        return ComUidLupd;
    }

    public void setComUidLupd(String comUidLupd) {
        ComUidLupd = comUidLupd;
    }

    @Override
    public int compareTo(TMstCommune o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

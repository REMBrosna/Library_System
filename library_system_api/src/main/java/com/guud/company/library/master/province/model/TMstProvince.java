package com.guud.company.library.master.province.model;

import com.guud.company.library.core.COAbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_MST_PROVINCE")
public class TMstProvince extends COAbstractEntity<TMstProvince> {
    private static final long serialVersionUID = 5639161183568020457L;

    private String code;
    private String desc;
    private String descOth;
    private char recStatus;
    private Date dtCreate;
    private String uidCreate;
    private Date dtLupd;
    private String uidLupd;

    public TMstProvince() {
    }

    public TMstProvince(String code, String desc, String descOth) {
        this.code = code;
        this.desc = desc;
        this.descOth = descOth;
    }
    @Id
    @Column(name = "PRO_CODE", unique = true, nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "PRO_DESC", nullable = false, length = 512)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "PRO_DESC_OTH", length = 512)
    public String getDescOth() {
        return descOth;
    }

    public void setDescOth(String descOth) {
        this.descOth = descOth;
    }

    @Column(name = "PRO_REC_STATUS", nullable = false, length = 1)
    public char getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(char recStatus) {
        this.recStatus = recStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRO_DT_CREATE", length = 19)
    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Column(name = "PRO_UID_CREATE", length = 35)
    public String getUidCreate() {
        return uidCreate;
    }

    public void setUidCreate(String uidCreate) {
        this.uidCreate = uidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRO_DT_LUPD", length = 19)
    public Date getDtLupd() {
        return dtLupd;
    }

    public void setDtLupd(Date dtLupd) {
        this.dtLupd = dtLupd;
    }

    @Column(name = "PRO_UID_LUPD", length = 35)
    public String getUidLupd() {
        return uidLupd;
    }

    public void setUidLupd(String uidLupd) {
        this.uidLupd = uidLupd;
    }

    @Override
    public int compareTo(TMstProvince o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

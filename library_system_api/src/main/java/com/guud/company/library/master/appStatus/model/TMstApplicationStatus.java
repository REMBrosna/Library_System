package com.guud.company.library.master.appStatus.model;

import com.guud.company.library.core.COAbstractEntity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_APPLICATION_STATUS")
public class TMstApplicationStatus extends COAbstractEntity<TMstApplicationStatus> {

    private static final long serialVersionUID = 1984944824054042763L;
    private String apsCode;
    private String apsDesc;
    private String apsDescOth;
    private char apsRecStatus;
    private Date apsDtCreate;
    private String apsUidCreate;
    private Date apsDtLupd;
    private String apsUidLupd;

    public TMstApplicationStatus() {
    }

    public TMstApplicationStatus(String apsCode, String apsDesc, String apsDescOth, char apsRecStatus, Date apsDtCreate, String apsUidCreate, Date apsDtLupd, String apsUidLupd) {
        this.apsCode = apsCode;
        this.apsDesc = apsDesc;
        this.apsDescOth = apsDescOth;
        this.apsRecStatus = apsRecStatus;
        this.apsDtCreate = apsDtCreate;
        this.apsUidCreate = apsUidCreate;
        this.apsDtLupd = apsDtLupd;
        this.apsUidLupd = apsUidLupd;
    }

    @Id
    @Column(name = "APS_CODE", unique = true, nullable = false, length = 45)
    public String getApsCode() {
        return apsCode;
    }

    public void setApsCode(String apsCode) {
        this.apsCode = apsCode;
    }

    @Column(name = "APS_DESC", nullable = false, length = 128)
    public String getApsDesc() {
        return apsDesc;
    }

    public void setApsDesc(String apsDesc) {
        this.apsDesc = apsDesc;
    }

    @Column(name = "APS_DESC_OTH", length = 128)
    public String getApsDescOth() {
        return apsDescOth;
    }

    public void setApsDescOth(String apsDescOth) {
        this.apsDescOth = apsDescOth;
    }

    @Column(name = "APS_REC_STATUS", nullable = false, length = 1)
    public char getApsRecStatus() {
        return apsRecStatus;
    }

    public void setApsRecStatus(char apsRecStatus) {
        this.apsRecStatus = apsRecStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APS_DT_CREATE", length = 19)
    public Date getApsDtCreate() {
        return apsDtCreate;
    }

    public void setApsDtCreate(Date apsDtCreate) {
        this.apsDtCreate = apsDtCreate;
    }

    @Column(name = "APS_UID_CREATE", length = 255)
    public String getApsUidCreate() {
        return apsUidCreate;
    }

    public void setApsUidCreate(String apsUidCreate) {
        this.apsUidCreate = apsUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APS_DT_LUPD", length = 19)
    public Date getApsDtLupd() {
        return apsDtLupd;
    }

    public void setApsDtLupd(Date apsDtLupd) {
        this.apsDtLupd = apsDtLupd;
    }

    @Column(name = "APS_UID_LUPD", length = 255)
    public String getApsUidLupd() {
        return apsUidLupd;
    }

    public void setApsUidLupd(String apsUidLupd) {
        this.apsUidLupd = apsUidLupd;
    }

    @Override
    public int compareTo(TMstApplicationStatus o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

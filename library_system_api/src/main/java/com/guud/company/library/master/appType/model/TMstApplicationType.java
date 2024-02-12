package com.guud.company.library.master.appType.model;

import com.guud.company.library.core.COAbstractEntity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_APPLICATION_TYPE")
public class TMstApplicationType extends COAbstractEntity<TMstApplicationType> {

    private static final long serialVersionUID = -658968274718980215L;
    private String aptCode;
    private String aptDesc;
    private String aptDescOth;
    private char aptRecStatus;
    private Date aptDtCreate;
    private String aptUidCreate;
    private Date aptDtLupd;
    private String aptUidLupd;

    public TMstApplicationType(String aptCode, String aptDesc, String aptDescOth, char aptRecStatus, Date aptDtCreate, String aptUidCreate, Date aptDtLupd, String aptUidLupd) {
        this.aptCode = aptCode;
        this.aptDesc = aptDesc;
        this.aptDescOth = aptDescOth;
        this.aptRecStatus = aptRecStatus;
        this.aptDtCreate = aptDtCreate;
        this.aptUidCreate = aptUidCreate;
        this.aptDtLupd = aptDtLupd;
        this.aptUidLupd = aptUidLupd;
    }

    public TMstApplicationType() {
    }

    @Id
    @Column(name = "APT_CODE", unique = true, nullable = false, length = 45)
    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    @Column(name = "APT_DESC", nullable = false, length = 128)
    public String getAptDesc() {
        return aptDesc;
    }

    public void setAptDesc(String aptDesc) {
        this.aptDesc = aptDesc;
    }

    @Column(name = "APT_DESC_OTH", length = 128)
    public String getAptDescOth() {
        return aptDescOth;
    }

    public void setAptDescOth(String aptDescOth) {
        this.aptDescOth = aptDescOth;
    }

    @Column(name = "APT_REC_STATUS", nullable = false, length = 1)
    public char getAptRecStatus() {
        return aptRecStatus;
    }

    public void setAptRecStatus(char aptRecStatus) {
        this.aptRecStatus = aptRecStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APT_DT_CREATE", length = 19)
    public Date getAptDtCreate() {
        return aptDtCreate;
    }

    public void setAptDtCreate(Date aptDtCreate) {
        this.aptDtCreate = aptDtCreate;
    }

    @Column(name = "APT_UID_CREATE", length = 255)
    public String getAptUidCreate() {
        return aptUidCreate;
    }

    public void setAptUidCreate(String aptUidCreate) {
        this.aptUidCreate = aptUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APT_DT_LUPD", length = 19)
    public Date getAptDtLupd() {
        return aptDtLupd;
    }

    public void setAptDtLupd(Date aptDtLupd) {
        this.aptDtLupd = aptDtLupd;
    }

    @Column(name = "APT_UID_LUPD", length = 255)
    public String getAptUidLupd() {
        return aptUidLupd;
    }

    public void setAptUidLupd(String aptUidLupd) {
        this.aptUidLupd = aptUidLupd;
    }

    @Override
    public int compareTo(TMstApplicationType o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

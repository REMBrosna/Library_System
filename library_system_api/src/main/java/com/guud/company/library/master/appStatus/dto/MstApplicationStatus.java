package com.guud.company.library.master.appStatus.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import java.util.Date;

public class MstApplicationStatus extends AbstractDTO<MstApplicationStatus, TMstApplicationStatus> {

    private static final long serialVersionUID = -7655556458828109250L;
    private String apsCode;
    private String apsDesc;
    private String apsDescOth;
    private char apsRecStatus;
    private Date apsDtCreate;
    private String apsUidCreate;
    private Date apsDtLupd;
    private String apsUidLupd;

    public MstApplicationStatus() {
    }

    public MstApplicationStatus(TMstApplicationStatus entity) {
        super(entity);
    }

    public MstApplicationStatus(String apsCode, String apsDesc, String apsDescOth, char apsRecStatus, Date apsDtCreate, String apsUidCreate, Date apsDtLupd, String apsUidLupd) {
        this.apsCode = apsCode;
        this.apsDesc = apsDesc;
        this.apsDescOth = apsDescOth;
        this.apsRecStatus = apsRecStatus;
        this.apsDtCreate = apsDtCreate;
        this.apsUidCreate = apsUidCreate;
        this.apsDtLupd = apsDtLupd;
        this.apsUidLupd = apsUidLupd;
    }

    public String getApsCode() {
        return apsCode;
    }

    public void setApsCode(String apsCode) {
        this.apsCode = apsCode;
    }

    public String getApsDesc() {
        return apsDesc;
    }

    public void setApsDesc(String apsDesc) {
        this.apsDesc = apsDesc;
    }

    public String getApsDescOth() {
        return apsDescOth;
    }

    public void setApsDescOth(String apsDescOth) {
        this.apsDescOth = apsDescOth;
    }

    public char getApsRecStatus() {
        return apsRecStatus;
    }

    public void setApsRecStatus(char apsRecStatus) {
        this.apsRecStatus = apsRecStatus;
    }

    public Date getApsDtCreate() {
        return apsDtCreate;
    }

    public void setApsDtCreate(Date apsDtCreate) {
        this.apsDtCreate = apsDtCreate;
    }

    public String getApsUidCreate() {
        return apsUidCreate;
    }

    public void setApsUidCreate(String apsUidCreate) {
        this.apsUidCreate = apsUidCreate;
    }

    public Date getApsDtLupd() {
        return apsDtLupd;
    }

    public void setApsDtLupd(Date apsDtLupd) {
        this.apsDtLupd = apsDtLupd;
    }

    public String getApsUidLupd() {
        return apsUidLupd;
    }

    public void setApsUidLupd(String apsUidLupd) {
        this.apsUidLupd = apsUidLupd;
    }

    @Override
    public int compareTo(MstApplicationStatus o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

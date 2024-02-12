package com.guud.company.library.master.appType.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import java.util.Date;

public class MstApplicationType extends AbstractDTO<MstApplicationType, TMstApplicationType> {

    private static final long serialVersionUID = 8445068929817862022L;
    private String aptCode;
    private String aptDesc;
    private String aptDescOth;
    private char aptRecStatus;
    private Date aptDtCreate;
    private String aptUidCreate;
    private Date aptDtLupd;
    private String aptUidLupd;

    public MstApplicationType(String aptCode, String aptDesc, String aptDescOth, char aptRecStatus, Date aptDtCreate, String aptUidCreate, Date aptDtLupd, String aptUidLupd) {
        this.aptCode = aptCode;
        this.aptDesc = aptDesc;
        this.aptDescOth = aptDescOth;
        this.aptRecStatus = aptRecStatus;
        this.aptDtCreate = aptDtCreate;
        this.aptUidCreate = aptUidCreate;
        this.aptDtLupd = aptDtLupd;
        this.aptUidLupd = aptUidLupd;
    }

    public MstApplicationType() {
    }

    public MstApplicationType(TMstApplicationType entity) {
        super(entity);
    }

    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    public String getAptDesc() {
        return aptDesc;
    }

    public void setAptDesc(String aptDesc) {
        this.aptDesc = aptDesc;
    }

    public String getAptDescOth() {
        return aptDescOth;
    }

    public void setAptDescOth(String aptDescOth) {
        this.aptDescOth = aptDescOth;
    }

    public char getAptRecStatus() {
        return aptRecStatus;
    }

    public void setAptRecStatus(char aptRecStatus) {
        this.aptRecStatus = aptRecStatus;
    }

    public Date getAptDtCreate() {
        return aptDtCreate;
    }

    public void setAptDtCreate(Date aptDtCreate) {
        this.aptDtCreate = aptDtCreate;
    }

    public String getAptUidCreate() {
        return aptUidCreate;
    }

    public void setAptUidCreate(String aptUidCreate) {
        this.aptUidCreate = aptUidCreate;
    }

    public Date getAptDtLupd() {
        return aptDtLupd;
    }

    public void setAptDtLupd(Date aptDtLupd) {
        this.aptDtLupd = aptDtLupd;
    }

    public String getAptUidLupd() {
        return aptUidLupd;
    }

    public void setAptUidLupd(String aptUidLupd) {
        this.aptUidLupd = aptUidLupd;
    }

    @Override
    public int compareTo(MstApplicationType o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

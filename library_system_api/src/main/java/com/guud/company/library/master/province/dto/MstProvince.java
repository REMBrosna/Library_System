package com.guud.company.library.master.province.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.province.model.TMstProvince;

import java.util.Date;

public class MstProvince extends AbstractDTO<MstProvince, TMstProvince> {

    private static final long serialVersionUID = 1539640066546972372L;
    private String code;
    private String desc;
    private String descOth;
    private char recStatus;
    private Date dtCreate;
    private String uidCreate;
    private Date dtLupd;
    private String uidLupd;

    public MstProvince() {
    }

    public MstProvince(String code, String desc, String descOth, char recStatus, Date dtCreate, String uidCreate, Date dtLupd, String uidLupd) {
        this.code = code;
        this.desc = desc;
        this.descOth = descOth;
        this.recStatus = recStatus;
        this.dtCreate = dtCreate;
        this.uidCreate = uidCreate;
        this.dtLupd = dtLupd;
        this.uidLupd = uidLupd;
    }

    public MstProvince(TMstProvince entity) {
        super(entity);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescOth() {
        return descOth;
    }

    public void setDescOth(String descOth) {
        this.descOth = descOth;
    }

    public char getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(char recStatus) {
        this.recStatus = recStatus;
    }

    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getUidCreate() {
        return uidCreate;
    }

    public void setUidCreate(String uidCreate) {
        this.uidCreate = uidCreate;
    }

    public Date getDtLupd() {
        return dtLupd;
    }

    public void setDtLupd(Date dtLupd) {
        this.dtLupd = dtLupd;
    }

    public String getUidLupd() {
        return uidLupd;
    }

    public void setUidLupd(String uidLupd) {
        this.uidLupd = uidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(MstProvince o) {
        return 0;
    }
}

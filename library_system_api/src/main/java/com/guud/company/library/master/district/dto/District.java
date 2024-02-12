package com.guud.company.library.master.district.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.district.model.TMstDistrict;
import com.guud.company.library.master.province.model.TMstProvince;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class District extends AbstractDTO<District, TMstDistrict> {

    private static final long serialVersionUID = 1539640066546972372L;

    private String code;
    private String desc;
    private String descOth;
    private TMstProvince Province;
    private char recStatus;
    private Date DisDtCreate;
    private String DisUidCreate;
    private Date DisDtLupd;
    private String DisUidLupd;

    public District() {
    }

    public District(String code, String desc, String descOth, TMstProvince province, char recStatus, Date disDtCreate, String disUidCreate, Date disDtLupd, String disUidLupd) {
        this.code = code;
        this.desc = desc;
        this.descOth = descOth;
        Province = province;
        this.recStatus = recStatus;
        DisDtCreate = disDtCreate;
        DisUidCreate = disUidCreate;
        DisDtLupd = disDtLupd;
        DisUidLupd = disUidLupd;
    }

    public District(TMstDistrict entity) {
        super(entity);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public TMstProvince getProvince() {
        return Province;
    }

    public void setProvince(TMstProvince province) {
        Province = province;
    }

    public char getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(char recStatus) {
        this.recStatus = recStatus;
    }

    public Date getDisDtCreate() {
        return DisDtCreate;
    }

    public void setDisDtCreate(Date disDtCreate) {
        DisDtCreate = disDtCreate;
    }

    public String getDisUidCreate() {
        return DisUidCreate;
    }

    public void setDisUidCreate(String disUidCreate) {
        DisUidCreate = disUidCreate;
    }

    public Date getDisDtLupd() {
        return DisDtLupd;
    }

    public void setDisDtLupd(Date disDtLupd) {
        DisDtLupd = disDtLupd;
    }

    public String getDisUidLupd() {
        return DisUidLupd;
    }

    public void setDisUidLupd(String disUidLupd) {
        DisUidLupd = disUidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull District o) {
        return 0;
    }
}

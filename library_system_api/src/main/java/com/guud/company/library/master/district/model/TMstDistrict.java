package com.guud.company.library.master.district.model;

import com.guud.company.library.core.COAbstractEntity;
import com.guud.company.library.master.province.model.TMstProvince;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_MST_DISTRICT")
public class TMstDistrict extends COAbstractEntity<TMstDistrict> {
    private static final long serialVersionUID = 5639161183568020457L;

    private String code;
    private String desc;
    private String descOth;
    private TMstProvince Province;
    private char recStatus;
    private Date DisDtCreate;
    private String DisUidCreate;
    private Date DisDtLupd;
    private String DisUidLupd;

    public TMstDistrict() {
    }

    public TMstDistrict(String code, String desc, String descOth, TMstProvince province, char recStatus, Date disDtCreate, String disUidCreate, Date disDtLupd, String disUidLupd) {
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

    @Id
    @Column(name = "DIS_CODE", unique = true, nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "DIS_DESC", nullable = false, length = 512)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Column(name = "DIS_DESC_OTH", length = 512)
    public String getDescOth() {
        return descOth;
    }

    public void setDescOth(String descOth) {
        this.descOth = descOth;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIS_PRO_ID")
    public TMstProvince getProvince() {
        return Province;
    }

    public void setProvince(TMstProvince province) {
        Province = province;
    }

    @Column(name = "DIS_REC_STATUS", nullable = false, length = 1)
    public char getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(char recStatus) {
        this.recStatus = recStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DIS_DT_CREATE", length = 19)
    public Date getDisDtCreate() {
        return DisDtCreate;
    }

    public void setDisDtCreate(Date disDtCreate) {
        DisDtCreate = disDtCreate;
    }
    @Column(name = "DIS_UID_CREATE", length = 35)
    public String getDisUidCreate() {
        return DisUidCreate;
    }

    public void setDisUidCreate(String disUidCreate) {
        DisUidCreate = disUidCreate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DIS_DT_LUPD", length = 19)
    public Date getDisDtLupd() {
        return DisDtLupd;
    }

    public void setDisDtLupd(Date disDtLupd) {
        DisDtLupd = disDtLupd;
    }
    @Column(name = "DIS_UID_LUPD", length = 35)
    public String getDisUidLupd() {
        return DisUidLupd;
    }

    public void setDisUidLupd(String disUidLupd) {
        DisUidLupd = disUidLupd;
    }

    @Override
    public int compareTo(TMstDistrict o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

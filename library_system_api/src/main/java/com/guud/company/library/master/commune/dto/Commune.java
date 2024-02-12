package com.guud.company.library.master.commune.dto;


import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.commune.model.TMstCommune;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Commune extends AbstractDTO<Commune, TMstCommune> {

    private static final long serialVersionUID = 1539640066546972372L;

    private String code;
    private String desc;
    private String descOth;
    private char recStatus;
    private Date ComDtCreate;
    private String ComUidCreate;
    private Date ComDtLupd;
    private String ComUidLupd;

    public Commune() {
    }

    public Commune(String code, String desc, String descOth, char recStatus, Date comDtCreate, String comUidCreate, Date comDtLupd, String comUidLupd) {
        this.code = code;
        this.desc = desc;
        this.descOth = descOth;
        this.recStatus = recStatus;
        ComDtCreate = comDtCreate;
        ComUidCreate = comUidCreate;
        ComDtLupd = comDtLupd;
        ComUidLupd = comUidLupd;
    }
    public Commune(TMstCommune entity) {
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

    public Date getComDtCreate() {
        return ComDtCreate;
    }

    public void setComDtCreate(Date comDtCreate) {
        ComDtCreate = comDtCreate;
    }

    public String getComUidCreate() {
        return ComUidCreate;
    }

    public void setComUidCreate(String comUidCreate) {
        ComUidCreate = comUidCreate;
    }

    public Date getComDtLupd() {
        return ComDtLupd;
    }

    public void setComDtLupd(Date comDtLupd) {
        ComDtLupd = comDtLupd;
    }

    public String getComUidLupd() {
        return ComUidLupd;
    }

    public void setComUidLupd(String comUidLupd) {
        ComUidLupd = comUidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull Commune o) {
        return 0;
    }
}

package com.guud.company.library.sysparam.model;

import com.guud.company.library.core.COAbstractEntity;
import org.jetbrains.annotations.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_SYSTEM_PARAM")
public class TSystemParam extends COAbstractEntity<TSystemParam> {

    private static final long serialVersionUID = 7440524726799017157L;

    private String sypKey;
    private String sypVal;
    private String sypDesc;
    private char sypRecStatus;

    public TSystemParam() {
    }

    public TSystemParam(String sypKey, String sypVal, String sypDesc, char sypRecStatus) {
        this.sypKey = sypKey;
        this.sypVal = sypVal;
        this.sypDesc = sypDesc;
        this.sypRecStatus = sypRecStatus;
    }

    @Id
    @Column(name = "SYP_KEY", unique = true, nullable = false, length = 35)
    public String getSypKey() {
        return sypKey;
    }

    public void setSypKey(String sypKey) {
        this.sypKey = sypKey;
    }

    @Column(name = "SYP_VAL", length = 256)
    public String getSypVal() {
        return sypVal;
    }

    public void setSypVal(String sypVal) {
        this.sypVal = sypVal;
    }

    @Column(name = "SYP_DESC", length = 256)
    public String getSypDesc() {
        return sypDesc;
    }

    public void setSypDesc(String sypDesc) {
        this.sypDesc = sypDesc;
    }

    @Column(name = "SYP_REC_STATUS", nullable = false, length = 1)
    public char getSypRecStatus() {
        return sypRecStatus;
    }

    public void setSypRecStatus(char sypRecStatus) {
        this.sypRecStatus = sypRecStatus;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull TSystemParam o) {
        return 0;
    }
}

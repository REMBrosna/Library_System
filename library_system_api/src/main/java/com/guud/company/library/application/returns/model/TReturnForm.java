package com.guud.company.library.application.returns.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.core.COAbstractEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_RETURN_FORM")
public class TReturnForm extends COAbstractEntity<TReturnForm> {

    private static final long serialVersionUID = -6094122850735470987L;
    private String retId;
    private TApplication TApplication;
    private TApplication borApplication;
    private int returnQty;
    private Double retPenaltyAmount;
    private Date retBorrow;
    private String returnStatus;
    private Character recStatus;
    private Date retDtCreate;
    private String retUidCreate;
    private Date retDtLupd;
    private String retUidLupd;

    @Override
    public void init() {

    }
    public TReturnForm() {
    }

    public TReturnForm(String retId, TApplication TApplication, TApplication borApplication, int returnQty, Double retPenaltyAmount, Date retBorrow, String returnStatus, Character recStatus, Date retDtCreate, String retUidCreate, Date retDtLupd, String retUidLupd) {
        this.retId = retId;
        this.TApplication = TApplication;
        this.borApplication = borApplication;
        this.returnQty = returnQty;
        this.retPenaltyAmount = retPenaltyAmount;
        this.retBorrow = retBorrow;
        this.returnStatus = returnStatus;
        this.recStatus = recStatus;
        this.retDtCreate = retDtCreate;
        this.retUidCreate = retUidCreate;
        this.retDtLupd = retDtLupd;
        this.retUidLupd = retUidLupd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "RET_ID")
    public String getRetId() {
        return retId;
    }

    public void setRetId(String retId) {
        this.retId = retId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RET_APPLICATION")
    public TApplication getTApplication() {
        return TApplication;
    }

    public void setTApplication(com.guud.company.library.application.apps.model.TApplication TApplication) {
        this.TApplication = TApplication;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RET_BORROW_APPLICATION")
    public TApplication getBorApplication() {
        return borApplication;
    }

    public void setBorApplication(TApplication borApplication) {
        this.borApplication = borApplication;
    }

    @Column(name = "RET_QTY")
    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }
    @Column(name = "RET_PENALTY_AMOUNT")
    public Double getRetPenaltyAmount() {
        return retPenaltyAmount;
    }

    public void setRetPenaltyAmount(Double retPenaltyAmount) {
        this.retPenaltyAmount = retPenaltyAmount;
    }


    @CreationTimestamp
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "RET_BORROW_DATE")
    public Date getRetBorrow() {
        return retBorrow;
    }

    public void setRetBorrow(Date retBorrow) {
        this.retBorrow = retBorrow;
    }
    @Column(name = "RET_STATUS")
    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    @Column(name = "RET_REC_STATUS")
    public Character getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(Character recStatus) {
        this.recStatus = recStatus;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RET_DT_CREATE", length = 19)
    public Date getRetDtCreate() {
        return retDtCreate;
    }

    public void setRetDtCreate(Date retDtCreate) {
        this.retDtCreate = retDtCreate;
    }
    @Column(name = "RET_UID_CREATE", length = 255)
    public String getRetUidCreate() {
        return retUidCreate;
    }

    public void setRetUidCreate(String retUidCreate) {
        this.retUidCreate = retUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RET_DT_LUPD", length = 19)
    public Date getRetDtLupd() {
        return retDtLupd;
    }

    public void setRetDtLupd(Date retDtLupd) {
        this.retDtLupd = retDtLupd;
    }
    @Column(name = "RET_UID_LUPD", length = 255)
    public String getRetUidLupd() {
        return retUidLupd;
    }

    public void setRetUidLupd(String retUidLupd) {
        this.retUidLupd = retUidLupd;
    }

    @Override
    public int compareTo(@NotNull TReturnForm o) {
        return 0;
    }
}

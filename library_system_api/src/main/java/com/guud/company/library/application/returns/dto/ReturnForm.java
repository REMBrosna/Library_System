package com.guud.company.library.application.returns.dto;

import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.application.returns.model.TReturnForm;
import com.guud.company.library.core.AbstractDTO;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class ReturnForm extends AbstractDTO<ReturnForm, TReturnForm> {

    private static final long serialVersionUID = 3846534017607559931L;
    private String retId;
    private Application application;
    private Application borApplication;
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

    public ReturnForm() {
    }


    public ReturnForm(String retId, Application application, Application borApplication, int returnQty, Double retPenaltyAmount, Date retBorrow, String returnStatus, Character recStatus, Date retDtCreate, String retUidCreate, Date retDtLupd, String retUidLupd) {
        this.retId = retId;
        this.application = application;
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
    public ReturnForm(TReturnForm entity) {
    }
    public String getRetId() {
        return retId;
    }

    public void setRetId(String retId) {
        this.retId = retId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Application getBorApplication() {
        return borApplication;
    }

    public void setBorApplication(Application borApplication) {
        this.borApplication = borApplication;
    }

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    public Double getRetPenaltyAmount() {
        return retPenaltyAmount;
    }

    public void setRetPenaltyAmount(Double retPenaltyAmount) {
        this.retPenaltyAmount = retPenaltyAmount;
    }

    public Date getRetBorrow() {
        return retBorrow;
    }

    public void setRetBorrow(Date retBorrow) {
        this.retBorrow = retBorrow;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Character getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(Character recStatus) {
        this.recStatus = recStatus;
    }

    public Date getRetDtCreate() {
        return retDtCreate;
    }

    public void setRetDtCreate(Date retDtCreate) {
        this.retDtCreate = retDtCreate;
    }

    public String getRetUidCreate() {
        return retUidCreate;
    }

    public void setRetUidCreate(String retUidCreate) {
        this.retUidCreate = retUidCreate;
    }

    public Date getRetDtLupd() {
        return retDtLupd;
    }

    public void setRetDtLupd(Date retDtLupd) {
        this.retDtLupd = retDtLupd;
    }

    public String getRetUidLupd() {
        return retUidLupd;
    }

    public void setRetUidLupd(String retUidLupd) {
        this.retUidLupd = retUidLupd;
    }

    @Override
    public int compareTo(@NotNull ReturnForm o) {
        return 0;
    }
}

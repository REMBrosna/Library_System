package com.guud.company.library.application.borrow.dto;

import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.borrow.model.TBorrowForm;
import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.administrator.domain.AppUser;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowForm extends AbstractDTO<BorrowForm, TBorrowForm> {

    private static final long serialVersionUID = 3846534017607559931L;
    private String borId;
    private AppUser borCustomer;
    private Application Application;
    private int borTotalQty;
    private int borTotalOwe;
    private BigDecimal borPenaltyAmount;
    private Date borDtBorrow;
    private Date borDtReturn;
    private char borRecStatus;
    private Date borDtCreate;
    private String borUidCreate;
    private Date borDtLupd;
    private String borUidLupd;

    public BorrowForm() {
    }

    public BorrowForm(TBorrowForm entity) {
        super(entity);
    }

    public BorrowForm(String borId, AppUser borCustomer, com.guud.company.library.application.apps.dto.Application application, int borTotalQty, int borTotalOwe, BigDecimal borPenaltyAmount, Date borDtBorrow, Date borDtReturn, char borRecStatus, Date borDtCreate, String borUidCreate, Date borDtLupd, String borUidLupd) {
        this.borId = borId;
        this.borCustomer = borCustomer;
        this.Application = application;
        this.borTotalQty = borTotalQty;
        this.borTotalOwe = borTotalOwe;
        this.borPenaltyAmount = borPenaltyAmount;
        this.borDtBorrow = borDtBorrow;
        this.borDtReturn = borDtReturn;
        this.borRecStatus = borRecStatus;
        this.borDtCreate = borDtCreate;
        this.borUidCreate = borUidCreate;
        this.borDtLupd = borDtLupd;
        this.borUidLupd = borUidLupd;
    }

    public String getBorId() {
        return borId;
    }

    public void setBorId(String borId) {
        this.borId = borId;
    }

    public AppUser getBorCustomer() {
        return borCustomer;
    }

    public void setBorCustomer(AppUser borCustomer) {
        this.borCustomer = borCustomer;
    }

    public Application getApplication() {
        return Application;
    }

    public void setApplication(Application application) {
        Application = application;
    }

    public int getBorTotalQty() {
        return borTotalQty;
    }

    public void setBorTotalQty(int borTotalQty) {
        this.borTotalQty = borTotalQty;
    }

    public int getBorTotalOwe() {
        return borTotalOwe;
    }

    public void setBorTotalOwe(int borTotalOwe) {
        this.borTotalOwe = borTotalOwe;
    }

    public BigDecimal getBorPenaltyAmount() {
        return borPenaltyAmount;
    }

    public void setBorPenaltyAmount(BigDecimal borPenaltyAmount) {
        this.borPenaltyAmount = borPenaltyAmount;
    }

    public Date getBorDtBorrow() {
        return borDtBorrow;
    }

    public void setBorDtBorrow(Date borDtBorrow) {
        this.borDtBorrow = borDtBorrow;
    }

    public Date getBorDtReturn() {
        return borDtReturn;
    }

    public void setBorDtReturn(Date borDtReturn) {
        this.borDtReturn = borDtReturn;
    }

    public char getBorRecStatus() {
        return borRecStatus;
    }

    public void setBorRecStatus(char borRecStatus) {
        this.borRecStatus = borRecStatus;
    }

    public Date getBorDtCreate() {
        return borDtCreate;
    }

    public void setBorDtCreate(Date borDtCreate) {
        this.borDtCreate = borDtCreate;
    }

    public String getBorUidCreate() {
        return borUidCreate;
    }

    public void setBorUidCreate(String borUidCreate) {
        this.borUidCreate = borUidCreate;
    }

    public Date getBorDtLupd() {
        return borDtLupd;
    }

    public void setBorDtLupd(Date borDtLupd) {
        this.borDtLupd = borDtLupd;
    }

    public String getBorUidLupd() {
        return borUidLupd;
    }

    public void setBorUidLupd(String borUidLupd) {
        this.borUidLupd = borUidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(BorrowForm o) {
        return 0;
    }
}

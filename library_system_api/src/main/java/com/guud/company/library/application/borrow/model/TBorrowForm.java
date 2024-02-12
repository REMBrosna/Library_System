package com.guud.company.library.application.borrow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.core.COAbstractEntity;
import com.guud.company.library.administrator.domain.AppUser;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_BORROW_FORM")
public class TBorrowForm extends COAbstractEntity<TBorrowForm> {

    private static final long serialVersionUID = -6094122850735470987L;
    private String borId;
    private AppUser borCustomer;
    private TApplication TApplication;
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

    public TBorrowForm() {
    }

    public TBorrowForm(String borId, AppUser borCustomer, com.guud.company.library.application.apps.model.TApplication TApplication, int borTotalQty, int borTotalOwe, BigDecimal borPenaltyAmount, Date borDtBorrow, Date borDtReturn, char borRecStatus, Date borDtCreate, String borUidCreate, Date borDtLupd, String borUidLupd) {
        this.borId = borId;
        this.borCustomer = borCustomer;
        this.TApplication = TApplication;
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

    @Id
    @Column(name = "BOR_ID", unique = true, nullable = false, length = 45)
    public String getBorId() {
        return borId;
    }

    public void setBorId(String borId) {
        this.borId = borId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOR_CUSTOMER")
    public AppUser getBorCustomer() {
        return borCustomer;
    }

    public void setBorCustomer(AppUser borCustomer) {
        this.borCustomer = borCustomer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOR_APPLICATION")
    @JsonBackReference
    public TApplication getTApplication() {
        return TApplication;
    }

    public void setTApplication(TApplication TApplication) {
        this.TApplication = TApplication;
    }

    @Column(name = "BOR_TOTAL_QTY")
    public int getBorTotalQty() {
        return borTotalQty;
    }

    public void setBorTotalQty(int borTotalQty) {
        this.borTotalQty = borTotalQty;
    }

    @Column(name = "BOR_TOTAL_OWE")
    public int getBorTotalOwe() {
        return borTotalOwe;
    }

    public void setBorTotalOwe(int borTotalOwe) {
        this.borTotalOwe = borTotalOwe;
    }

    @Column(name = "BOR_PENALTY_AMOUNT")
    public BigDecimal getBorPenaltyAmount() {
        return borPenaltyAmount;
    }

    public void setBorPenaltyAmount(BigDecimal borPenaltyAmount) {
        this.borPenaltyAmount = borPenaltyAmount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOR_DT_BORROW", length = 19)
    public Date getBorDtBorrow() {
        return borDtBorrow;
    }

    public void setBorDtBorrow(Date borDtBorrow) {
        this.borDtBorrow = borDtBorrow;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOR_DT_RETURN", length = 19)
    public Date getBorDtReturn() {
        return borDtReturn;
    }

    public void setBorDtReturn(Date borDtReturn) {
        this.borDtReturn = borDtReturn;
    }

    @Column(name = "BOR_REC_STATUS", nullable = false, length = 1)
    public char getBorRecStatus() {
        return borRecStatus;
    }

    public void setBorRecStatus(char borRecStatus) {
        this.borRecStatus = borRecStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOR_DT_CREATE", length = 19)
    public Date getBorDtCreate() {
        return borDtCreate;
    }

    public void setBorDtCreate(Date borDtCreate) {
        this.borDtCreate = borDtCreate;
    }

    @Column(name = "BOR_UID_CREATE", length = 255)
    public String getBorUidCreate() {
        return borUidCreate;
    }

    public void setBorUidCreate(String borUidCreate) {
        this.borUidCreate = borUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOR_DT_LUPD", length = 19)
    public Date getBorDtLupd() {
        return borDtLupd;
    }

    public void setBorDtLupd(Date borDtLupd) {
        this.borDtLupd = borDtLupd;
    }

    @Column(name = "BOR_UID_LUPD", length = 255)
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
    public int compareTo(TBorrowForm o) {
        return 0;
    }
}

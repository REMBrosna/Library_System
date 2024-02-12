package com.guud.company.library.master.users.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.users.model.TCustomer;

import java.util.Date;

public class Customer extends AbstractDTO<Customer, TCustomer> {

    private static final long serialVersionUID = 1539640066546972372L;
    private String cusId;
    private String customerName;
    private String customerType;
    private String gender;
    private Date dob;
    private String phoneNumber;
    private String identityCardNo;
    private char recStatus;
    private Date dtCreate;
    private String uidCreate;
    private Date dtLupd;
    private String uidLupd;

    public Customer() {
    }

    public Customer(TCustomer entity) {
        super(entity);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
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
    public int compareTo(Customer o) {
        return 0;
    }
}

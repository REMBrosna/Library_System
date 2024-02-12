package com.guud.company.library.master.users.model;

import com.guud.company.library.core.COAbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_CUSTOMER")
public class TCustomer extends COAbstractEntity<TCustomer> {

    private static final long serialVersionUID = 5639161183568020457L;
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

    public TCustomer() {
    }

    public TCustomer(String cusId, String customerName, String customerType, String gender, Date dob, String phoneNumber, String identityCardNo, char recStatus, Date dtCreate, String uidCreate, Date dtLupd, String uidLupd) {
        this.cusId = cusId;
        this.customerName = customerName;
        this.customerType = customerType;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.identityCardNo = identityCardNo;
        this.recStatus = recStatus;
        this.dtCreate = dtCreate;
        this.uidCreate = uidCreate;
        this.dtLupd = dtLupd;
        this.uidLupd = uidLupd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "CUS_ID")
    public String getCusId() {
        return cusId;
    }
    public void setCusId(String cusId) {
        this.cusId = cusId;
    }
    @Column(name = "CUSTOMER_NAME")
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @Column(name = "CUSTOMER_TYPE")
    public String getCustomerType() {
        return customerType;
    }
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    @Column(name = "CUSTOMER_GENDER")
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name = "DATE_OF_BIRTH")
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Column(name = "IDENTITY_CARD_NO")
    public String getIdentityCardNo() {
        return identityCardNo;
    }
    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }
    @Column(name = "REC_STATUS")
    public char getRecStatus() {
        return recStatus;
    }
    public void setRecStatus(char recStatus) {
        this.recStatus = recStatus;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CUS_DT_CREATE", length = 19)
    public Date getDtCreate() {
        return dtCreate;
    }
    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }
    @Column(name = "CUS_UID_CREATE", length = 35)
    public String getUidCreate() {
        return uidCreate;
    }
    public void setUidCreate(String uidCreate) {
        this.uidCreate = uidCreate;
    }
    @Column(name = "CUS_DT_LUPD", length = 19)
    public Date getDtLupd() {
        return dtLupd;
    }
    public void setDtLupd(Date dtLupd) {
        this.dtLupd = dtLupd;
    }
    @Column(name = "CUS_UID_LUPD", length = 35)
    public String getUidLupd() {
        return uidLupd;
    }
    public void setUidLupd(String uidLupd) {
        this.uidLupd = uidLupd;
    }
    @Override
    public int compareTo(TCustomer o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

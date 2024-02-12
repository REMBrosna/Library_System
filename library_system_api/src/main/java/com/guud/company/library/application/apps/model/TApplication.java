package com.guud.company.library.application.apps.model;

import com.guud.company.library.core.COAbstractEntity;
import com.guud.company.library.master.appStatus.model.TMstApplicationStatus;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_APPLICATION")
public class TApplication extends COAbstractEntity<TApplication> {

    private static final long serialVersionUID = 3916151888243441783L;

    private String appID;
    private TMstApplicationType TMstApplicationType;
    private TMstApplicationStatus TMstApplicationStatus;
    private Date appDtConfirm;
    private char appRecStatus;
    private Date appDtCreate;
    private String appUidCreate;
    private Date appDtLupd;
    private String appUidLupd;

    public TApplication() {
    }

    public TApplication(String appID, TMstApplicationType TMstApplicationType, TMstApplicationStatus TMstApplicationStatus, Date appDtConfirm, char appRecStatus, Date appDtCreate, String appUidCreate, Date appDtLupd, String appUidLupd) {
        this.appID = appID;
        this.TMstApplicationType = TMstApplicationType;
        this.TMstApplicationStatus = TMstApplicationStatus;
        this.appDtConfirm = appDtConfirm;
        this.appRecStatus = appRecStatus;
        this.appDtCreate = appDtCreate;
        this.appUidCreate = appUidCreate;
        this.appDtLupd = appDtLupd;
        this.appUidLupd = appUidLupd;
    }

    @Id
    @Column(name = "APP_ID", unique = true, nullable = false, length = 45)
    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_APP_TYPE")
    public TMstApplicationType getTMstApplicationType() {
        return TMstApplicationType;
    }

    public void setTMstApplicationType(TMstApplicationType TMstApplicationType) {
        this.TMstApplicationType = TMstApplicationType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_APP_STATUS")
    public TMstApplicationStatus getTMstApplicationStatus() {
        return TMstApplicationStatus;
    }

    public void setTMstApplicationStatus(TMstApplicationStatus TMstApplicationStatus) {
        this.TMstApplicationStatus = TMstApplicationStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APP_DT_CONFIRM", length = 19)
    public Date getAppDtConfirm() {
        return appDtConfirm;
    }

    public void setAppDtConfirm(Date appDtConfirm) {
        this.appDtConfirm = appDtConfirm;
    }

    @Column(name = "APP_REC_STATUS", nullable = false, length = 1)
    public char getAppRecStatus() {
        return appRecStatus;
    }

    public void setAppRecStatus(char appRecStatus) {
        this.appRecStatus = appRecStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APP_DT_CREATE", length = 19)
    public Date getAppDtCreate() {
        return appDtCreate;
    }

    public void setAppDtCreate(Date appDtCreate) {
        this.appDtCreate = appDtCreate;
    }

    @Column(name = "APP_UID_CREATE", length = 255)
    public String getAppUidCreate() {
        return appUidCreate;
    }

    public void setAppUidCreate(String appUidCreate) {
        this.appUidCreate = appUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APP_DT_LUPD", length = 19)
    public Date getAppDtLupd() {
        return appDtLupd;
    }

    public void setAppDtLupd(Date appDtLupd) {
        this.appDtLupd = appDtLupd;
    }

    @Column(name = "APP_UID_LUPD", length = 255)
    public String getAppUidLupd() {
        return appUidLupd;
    }

    public void setAppUidLupd(String appUidLupd) {
        this.appUidLupd = appUidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(TApplication o) {
        return 0;
    }
}

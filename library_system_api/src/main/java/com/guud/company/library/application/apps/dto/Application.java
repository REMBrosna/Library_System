package com.guud.company.library.application.apps.dto;

import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.appStatus.dto.MstApplicationStatus;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import java.util.Date;

public class Application extends AbstractDTO<Application, TApplication> {

    private static final long serialVersionUID = -3419627088215140441L;
    private String appID;
    private MstApplicationType mstApplicationType;
    private MstApplicationStatus mstApplicationStatus;
    private Date appDtConfirm;
    private char appRecStatus;
    private Date appDtCreate;
    private String appUidCreate;
    private Date appDtLupd;
    private String appUidLupd;

    public Application() {
    }

    public Application(TApplication entity) {
        super(entity);
    }

    public Application(String appID, MstApplicationType mstApplicationType, MstApplicationStatus mstApplicationStatus, Date appDtConfirm, char appRecStatus, Date appDtCreate, String appUidCreate, Date appDtLupd, String appUidLupd) {
        this.appID = appID;
        this.mstApplicationType = mstApplicationType;
        this.mstApplicationStatus = mstApplicationStatus;
        this.appDtConfirm = appDtConfirm;
        this.appRecStatus = appRecStatus;
        this.appDtCreate = appDtCreate;
        this.appUidCreate = appUidCreate;
        this.appDtLupd = appDtLupd;
        this.appUidLupd = appUidLupd;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public MstApplicationType getMstApplicationType() {
        return mstApplicationType;
    }

    public void setMstApplicationType(MstApplicationType mstApplicationType) {
        this.mstApplicationType = mstApplicationType;
    }

    public MstApplicationStatus getMstApplicationStatus() {
        return mstApplicationStatus;
    }

    public void setMstApplicationStatus(MstApplicationStatus mstApplicationStatus) {
        this.mstApplicationStatus = mstApplicationStatus;
    }

    public Date getAppDtConfirm() {
        return appDtConfirm;
    }

    public void setAppDtConfirm(Date appDtConfirm) {
        this.appDtConfirm = appDtConfirm;
    }

    public char getAppRecStatus() {
        return appRecStatus;
    }

    public void setAppRecStatus(char appRecStatus) {
        this.appRecStatus = appRecStatus;
    }

    public Date getAppDtCreate() {
        return appDtCreate;
    }

    public void setAppDtCreate(Date appDtCreate) {
        this.appDtCreate = appDtCreate;
    }

    public String getAppUidCreate() {
        return appUidCreate;
    }

    public void setAppUidCreate(String appUidCreate) {
        this.appUidCreate = appUidCreate;
    }

    public Date getAppDtLupd() {
        return appDtLupd;
    }

    public void setAppDtLupd(Date appDtLupd) {
        this.appDtLupd = appDtLupd;
    }

    public String getAppUidLupd() {
        return appUidLupd;
    }

    public void setAppUidLupd(String appUidLupd) {
        this.appUidLupd = appUidLupd;
    }

    @Override
    public int compareTo(Application o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

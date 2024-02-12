package com.guud.company.library.reports.model;

import com.guud.company.library.core.COAbstractEntity;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "T_REPORT_SERVICE_CONFIG")
public class TReportServiceConfig extends COAbstractEntity<TReportServiceConfig> {

    private static final long serialVersionUID = 4814335077405675252L;
    private Long resId;
    private String resVal;
    private String resServiceName;
    private String resDesc;
    private Character resRecStatus;

    public TReportServiceConfig() {
    }

    public TReportServiceConfig(Long resId, String resVal, String resServiceName, String resDesc, Character resRecStatus) {
        this.resId = resId;
        this.resVal = resVal;
        this.resServiceName = resServiceName;
        this.resDesc = resDesc;
        this.resRecStatus = resRecStatus;
    }

    @Id
    @GeneratedValue
    @Column(name = "RES_ID", nullable = false)
    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    @Column(name = "RES_VAL", nullable = false)
    public String getResVal() {
        return resVal;
    }

    public void setResVal(String resVal) {
        this.resVal = resVal;
    }

    @Column(name = "RES_SERVICE_NAME", nullable = false)
    public String getResServiceName() {
        return resServiceName;
    }

    public void setResServiceName(String resServiceName) {
        this.resServiceName = resServiceName;
    }

    @Column(name = "RES_DESC")
    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    @Column(name = "RES_REC_STATUS", length = 1)
    public Character getResRecStatus() {
        return resRecStatus;
    }

    public void setResRecStatus(Character resRecStatus) {
        this.resRecStatus = resRecStatus;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull TReportServiceConfig o) {
        return 0;
    }
}

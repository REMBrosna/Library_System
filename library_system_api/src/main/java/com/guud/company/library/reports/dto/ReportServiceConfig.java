package com.guud.company.library.reports.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.reports.model.TReportServiceConfig;
import org.jetbrains.annotations.NotNull;

public class ReportServiceConfig extends AbstractDTO<ReportServiceConfig, TReportServiceConfig> {

    private static final long serialVersionUID = -7009389913173288752L;
    private Long resId;
    private String resVal;
    private String resServiceName;
    private String resDesc;
    private Character resRecStatus;

    public ReportServiceConfig() {
    }

    public ReportServiceConfig(Long resId, String resVal, String resServiceName, String resDesc, Character resRecStatus) {
        this.resId = resId;
        this.resVal = resVal;
        this.resServiceName = resServiceName;
        this.resDesc = resDesc;
        this.resRecStatus = resRecStatus;
    }

    public ReportServiceConfig(TReportServiceConfig entity) {
        super(entity);
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResVal() {
        return resVal;
    }

    public void setResVal(String resVal) {
        this.resVal = resVal;
    }

    public String getResServiceName() {
        return resServiceName;
    }

    public void setResServiceName(String resServiceName) {
        this.resServiceName = resServiceName;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

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
    public int compareTo(@NotNull ReportServiceConfig o) {
        return 0;
    }
}

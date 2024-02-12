package com.guud.company.library.reports.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.reports.model.TReportTemplateConfig;
import org.jetbrains.annotations.NotNull;

public class ReportTemplateConfig extends AbstractDTO<ReportTemplateConfig, TReportTemplateConfig> {

    private static final long serialVersionUID = -8295797946699981340L;
    private Long retId;
    private ReportServiceConfig retService;
    private String retTemplateType;
    private String retSubTemplate;
    private String retTemplatePath;
    private String retTemplateLogo;
    private Character retCecStatus;

    public ReportTemplateConfig() {
    }

    public ReportTemplateConfig(Long retId, ReportServiceConfig retService, String retTemplateType, String retSubTemplate, String retTemplatePath, String retTemplateLogo, Character retCecStatus) {
        this.retId = retId;
        this.retService = retService;
        this.retTemplateType = retTemplateType;
        this.retSubTemplate = retSubTemplate;
        this.retTemplatePath = retTemplatePath;
        this.retTemplateLogo = retTemplateLogo;
        this.retCecStatus = retCecStatus;
    }

    public ReportTemplateConfig(TReportTemplateConfig entity) {
        super(entity);
    }

    public Long getRetId() {
        return retId;
    }

    public void setRetId(Long retId) {
        this.retId = retId;
    }

    public ReportServiceConfig getRetService() {
        return retService;
    }

    public void setRetService(ReportServiceConfig retService) {
        this.retService = retService;
    }

    public String getRetTemplateType() {
        return retTemplateType;
    }

    public void setRetTemplateType(String retTemplateType) {
        this.retTemplateType = retTemplateType;
    }

    public String getRetSubTemplate() {
        return retSubTemplate;
    }

    public void setRetSubTemplate(String retSubTemplate) {
        this.retSubTemplate = retSubTemplate;
    }

    public String getRetTemplatePath() {
        return retTemplatePath;
    }

    public void setRetTemplatePath(String retTemplatePath) {
        this.retTemplatePath = retTemplatePath;
    }

    public String getRetTemplateLogo() {
        return retTemplateLogo;
    }

    public void setRetTemplateLogo(String retTemplateLogo) {
        this.retTemplateLogo = retTemplateLogo;
    }

    public Character getRetCecStatus() {
        return retCecStatus;
    }

    public void setRetCecStatus(Character retCecStatus) {
        this.retCecStatus = retCecStatus;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull ReportTemplateConfig o) {
        return 0;
    }
}

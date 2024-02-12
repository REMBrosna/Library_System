package com.guud.company.library.reports.model;

import com.guud.company.library.core.COAbstractEntity;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "T_REPORT_TEMPLATE_CONFIG")
public class TReportTemplateConfig extends COAbstractEntity<TReportTemplateConfig> {

    private static final long serialVersionUID = 2064618594475186952L;

    private Long retId;
    private TReportServiceConfig retService;
    private String retTemplateType;
    private String retSubTemplate;
    private String retTemplatePath;
    private String retTemplateLogo;
    private Character retCecStatus;

    public TReportTemplateConfig() {
    }

    public TReportTemplateConfig(Long retId, TReportServiceConfig retService, String retTemplateType, String retSubTemplate, String retTemplatePath, String retTemplateLogo, Character retCecStatus) {
        this.retId = retId;
        this.retService = retService;
        this.retTemplateType = retTemplateType;
        this.retSubTemplate = retSubTemplate;
        this.retTemplatePath = retTemplatePath;
        this.retTemplateLogo = retTemplateLogo;
        this.retCecStatus = retCecStatus;
    }

    @Id
    @GeneratedValue
    @JoinColumn(name = "RET_ID", nullable = false)
    public Long getRetId() {
        return retId;
    }

    public void setRetId(Long retId) {
        this.retId = retId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RET_SERVICE", nullable = false)
    public TReportServiceConfig getRetService() {
        return retService;
    }

    public void setRetService(TReportServiceConfig retService) {
        this.retService = retService;
    }

    @Column(name = "RET_TEMPLATE_TYPE", nullable = false)
    public String getRetTemplateType() {
        return retTemplateType;
    }

    public void setRetTemplateType(String retTemplateType) {
        this.retTemplateType = retTemplateType;
    }

    @Column(name = "RET_SUB_TEMPLATE")
    public String getRetSubTemplate() {
        return retSubTemplate;
    }

    public void setRetSubTemplate(String retSubTemplate) {
        this.retSubTemplate = retSubTemplate;
    }

    @Column(name = "RET_TEMPLATE_PATH", nullable = false)
    public String getRetTemplatePath() {
        return retTemplatePath;
    }

    public void setRetTemplatePath(String retTemplatePath) {
        this.retTemplatePath = retTemplatePath;
    }

    @Column(name = "RET_TEMPLATE_LOGO")
    public String getRetTemplateLogo() {
        return retTemplateLogo;
    }

    public void setRetTemplateLogo(String retTemplateLogo) {
        this.retTemplateLogo = retTemplateLogo;
    }

    @Column(name = "RET_REC_STATUS", length = 1, nullable = false)
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
    public int compareTo(@NotNull TReportTemplateConfig o) {
        return 0;
    }
}

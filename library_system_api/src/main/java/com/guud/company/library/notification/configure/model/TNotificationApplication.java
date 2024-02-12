package com.guud.company.library.notification.configure.model;
import com.guud.company.library.core.COAbstractEntity;
import com.guud.company.library.master.appType.model.TMstApplicationType;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_NOTIFICATION_APP")
public class TNotificationApplication extends COAbstractEntity<TNotificationApplication> {

    private static final long serialVersionUID = -4332267580257361093L;
    private Long noaId;
    private TMstApplicationType TMstApplicationType;
    private String noaAction;
    private Long noaEmailTemplateId;
    private Character noaRequiredEmail;
    private Long noaTelegramTemplateId;
    private Character noaRequiredTelegram;
    private Character noaRecStatus;
    private Date noaDtCreate;
    private String noaUidCreate;
    private Date noaDtLupd;
    private String noaUidLupd;

    public TNotificationApplication() {
    }

    public TNotificationApplication(Long noaId, com.guud.company.library.master.appType.model.TMstApplicationType TMstApplicationType, String noaAction, Long noaEmailTemplateId, Character noaRequiredEmail, Long noaTelegramTemplateId, Character noaRequiredTelegram, Character noaRecStatus, Date noaDtCreate, String noaUidCreate, Date noaDtLupd, String noaUidLupd) {
        this.noaId = noaId;
        this.TMstApplicationType = TMstApplicationType;
        this.noaAction = noaAction;
        this.noaEmailTemplateId = noaEmailTemplateId;
        this.noaRequiredEmail = noaRequiredEmail;
        this.noaTelegramTemplateId = noaTelegramTemplateId;
        this.noaRequiredTelegram = noaRequiredTelegram;
        this.noaRecStatus = noaRecStatus;
        this.noaDtCreate = noaDtCreate;
        this.noaUidCreate = noaUidCreate;
        this.noaDtLupd = noaDtLupd;
        this.noaUidLupd = noaUidLupd;
    }

    @Id
    @GeneratedValue
    @Column(name = "NOA_ID")
    public Long getNoaId() {
        return noaId;
    }

    public void setNoaId(Long noaId) {
        this.noaId = noaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOA_APPLICATION_TYPE")
    public TMstApplicationType getTMstApplicationType() {
        return TMstApplicationType;
    }

    public void setTMstApplicationType(TMstApplicationType TMstApplicationType) {
        this.TMstApplicationType = TMstApplicationType;
    }

    @Column(name = "NOA_ACTION")
    public String getNoaAction() {
        return noaAction;
    }

    public void setNoaAction(String noaAction) {
        this.noaAction = noaAction;
    }

    @Column(name = "NOA_EMAIL_TEMPLATE")
    public Long getNoaEmailTemplateId() {
        return noaEmailTemplateId;
    }

    public void setNoaEmailTemplateId(Long noaEmailTemplateId) {
        this.noaEmailTemplateId = noaEmailTemplateId;
    }

    @Column(name = "NOA_EMAIL_REQUIRED", nullable = false, length = 1)
    public Character getNoaRequiredEmail() {
        return noaRequiredEmail;
    }

    public void setNoaRequiredEmail(Character noaRequiredEmail) {
        this.noaRequiredEmail = noaRequiredEmail;
    }

    @Column(name = "NOA_TELEGRAM_TEMPLATE")
    public Long getNoaTelegramTemplateId() {
        return noaTelegramTemplateId;
    }

    public void setNoaTelegramTemplateId(Long noaTelegramTemplateId) {
        this.noaTelegramTemplateId = noaTelegramTemplateId;
    }

    @Column(name = "NOA_TELEGRAM_REQUIRED", nullable = false, length = 1)
    public Character getNoaRequiredTelegram() {
        return noaRequiredTelegram;
    }

    public void setNoaRequiredTelegram(Character noaRequiredTelegram) {
        this.noaRequiredTelegram = noaRequiredTelegram;
    }

    @Column(name = "NOA_REC_STATUS", nullable = false, length = 1)
    public Character getNoaRecStatus() {
        return noaRecStatus;
    }

    public void setNoaRecStatus(Character noaRecStatus) {
        this.noaRecStatus = noaRecStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOA_DT_CREATE", length = 19)
    public Date getNoaDtCreate() {
        return noaDtCreate;
    }

    public void setNoaDtCreate(Date noaDtCreate) {
        this.noaDtCreate = noaDtCreate;
    }

    @Column(name = "NOA_UID_CREATE")
    public String getNoaUidCreate() {
        return noaUidCreate;
    }

    public void setNoaUidCreate(String noaUidCreate) {
        this.noaUidCreate = noaUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOA_DT_LUPD", length = 19)
    public Date getNoaDtLupd() {
        return noaDtLupd;
    }

    public void setNoaDtLupd(Date noaDtLupd) {
        this.noaDtLupd = noaDtLupd;
    }

    @Column(name = "NOA_UID_LUPD")
    public String getNoaUidLupd() {
        return noaUidLupd;
    }

    public void setNoaUidLupd(String noaUidLupd) {
        this.noaUidLupd = noaUidLupd;
    }

    @Override
    public int compareTo(@NotNull TNotificationApplication o) {
        return 0;
    }

    @Override
    public void init() {

    }
}

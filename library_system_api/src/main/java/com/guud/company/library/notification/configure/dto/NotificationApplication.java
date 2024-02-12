package com.guud.company.library.notification.configure.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.appType.dto.MstApplicationType;
import com.guud.company.library.notification.configure.model.TNotificationApplication;
import org.jetbrains.annotations.NotNull;
import java.util.Date;

public class NotificationApplication extends AbstractDTO<NotificationApplication, TNotificationApplication> {

    private static final long serialVersionUID = -8728722660448042909L;
    private Long noaId;
    private MstApplicationType MstApplicationType;
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

    public NotificationApplication() {
    }

    public NotificationApplication(TNotificationApplication entity) {
        super(entity);
    }

    public NotificationApplication(Long noaId, com.guud.company.library.master.appType.dto.MstApplicationType mstApplicationType, String noaAction, Long noaEmailTemplateId, Character noaRequiredEmail, Long noaTelegramTemplateId, Character noaRequiredTelegram, Character noaRecStatus, Date noaDtCreate, String noaUidCreate, Date noaDtLupd, String noaUidLupd) {
        this.noaId = noaId;
        MstApplicationType = mstApplicationType;
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

    public Long getNoaId() {
        return noaId;
    }

    public void setNoaId(Long noaId) {
        this.noaId = noaId;
    }

    public MstApplicationType getMstApplicationType() {
        return MstApplicationType;
    }

    public void setMstApplicationType(MstApplicationType mstApplicationType) {
        MstApplicationType = mstApplicationType;
    }

    public String getNoaAction() {
        return noaAction;
    }

    public void setNoaAction(String noaAction) {
        this.noaAction = noaAction;
    }

    public Long getNoaEmailTemplateId() {
        return noaEmailTemplateId;
    }

    public void setNoaEmailTemplateId(Long noaEmailTemplateId) {
        this.noaEmailTemplateId = noaEmailTemplateId;
    }

    public Character getNoaRequiredEmail() {
        return noaRequiredEmail;
    }

    public void setNoaRequiredEmail(Character noaRequiredEmail) {
        this.noaRequiredEmail = noaRequiredEmail;
    }

    public Long getNoaTelegramTemplateId() {
        return noaTelegramTemplateId;
    }

    public void setNoaTelegramTemplateId(Long noaTelegramTemplateId) {
        this.noaTelegramTemplateId = noaTelegramTemplateId;
    }

    public Character getNoaRequiredTelegram() {
        return noaRequiredTelegram;
    }

    public void setNoaRequiredTelegram(Character noaRequiredTelegram) {
        this.noaRequiredTelegram = noaRequiredTelegram;
    }

    public Character getNoaRecStatus() {
        return noaRecStatus;
    }

    public void setNoaRecStatus(Character noaRecStatus) {
        this.noaRecStatus = noaRecStatus;
    }

    public Date getNoaDtCreate() {
        return noaDtCreate;
    }

    public void setNoaDtCreate(Date noaDtCreate) {
        this.noaDtCreate = noaDtCreate;
    }

    public String getNoaUidCreate() {
        return noaUidCreate;
    }

    public void setNoaUidCreate(String noaUidCreate) {
        this.noaUidCreate = noaUidCreate;
    }

    public Date getNoaDtLupd() {
        return noaDtLupd;
    }

    public void setNoaDtLupd(Date noaDtLupd) {
        this.noaDtLupd = noaDtLupd;
    }

    public String getNoaUidLupd() {
        return noaUidLupd;
    }

    public void setNoaUidLupd(String noaUidLupd) {
        this.noaUidLupd = noaUidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull NotificationApplication o) {
        return 0;
    }
}

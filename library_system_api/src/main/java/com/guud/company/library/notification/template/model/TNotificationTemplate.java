package com.guud.company.library.notification.template.model;

import com.guud.company.library.core.COAbstractEntity;
import com.guud.company.library.notification.logs.model.TNotificationLogs;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "T_NOTIFICATION_TEMPLATE")
public class TNotificationTemplate extends COAbstractEntity<TNotificationLogs> {

    private static final long serialVersionUID = 6358402997937898615L;
    private Long notId;
    private String notChannelType;
    private String notSubject;
    private String notContentType;
    private String notContent;
    private String notDesc;
    private Character notRecStatus;

    public TNotificationTemplate() {
    }

    public TNotificationTemplate(Long notId, String notChannelType, String notSubject, String notContentType, String notContent, String notDesc, Character notRecStatus) {
        this.notId = notId;
        this.notChannelType = notChannelType;
        this.notSubject = notSubject;
        this.notContentType = notContentType;
        this.notContent = notContent;
        this.notDesc = notDesc;
        this.notRecStatus = notRecStatus;
    }

    @Id
    @GeneratedValue
    @Column(name = "NOT_ID")
    public Long getNotId() {
        return notId;
    }

    public void setNotId(Long notId) {
        this.notId = notId;
    }

    @Column(name = "NOT_CHANNEL_TYPE")
    public String getNotChannelType() {
        return notChannelType;
    }

    public void setNotChannelType(String notChannelType) {
        this.notChannelType = notChannelType;
    }

    @Column(name = "NOT_SUBJECT")
    public String getNotSubject() {
        return notSubject;
    }

    public void setNotSubject(String notSubject) {
        this.notSubject = notSubject;
    }

    @Column(name = "NOT_CONTENT_TYPE")
    public String getNotContentType() {
        return notContentType;
    }

    public void setNotContentType(String notContentType) {
        this.notContentType = notContentType;
    }

    @Column(name = "NOT_CONTENT")
    public String getNotContent() {
        return notContent;
    }

    public void setNotContent(String notContent) {
        this.notContent = notContent;
    }

    @Column(name = "NOT_DESC")
    public String getNotDesc() {
        return notDesc;
    }

    public void setNotDesc(String notDesc) {
        this.notDesc = notDesc;
    }

    @Column(name = "NOT_REC_STATUS", nullable = false, length = 1)
    public Character getNotRecStatus() {
        return notRecStatus;
    }

    public void setNotRecStatus(Character notRecStatus) {
        this.notRecStatus = notRecStatus;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull TNotificationLogs o) {
        return 0;
    }
}

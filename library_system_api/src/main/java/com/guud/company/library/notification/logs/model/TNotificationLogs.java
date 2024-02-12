package com.guud.company.library.notification.logs.model;

import com.guud.company.library.core.COAbstractEntity;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "T_NOTIFICATION_LOGS")
public class TNotificationLogs extends COAbstractEntity<TNotificationLogs> {

    private static final long serialVersionUID = -5436487979180618604L;
    private Long nolId;
    private String nolBody;
    private int nolRetry;
    private String nolType;
    private Character nolStatus;
    private Character nolRecStatus;

    public TNotificationLogs() {
    }

    public TNotificationLogs(Long nolId, String nolBody, int nolRetry, String nolType, Character nolStatus, Character nolRecStatus) {
        this.nolId = nolId;
        this.nolBody = nolBody;
        this.nolRetry = nolRetry;
        this.nolType = nolType;
        this.nolStatus = nolStatus;
        this.nolRecStatus = nolRecStatus;
    }

    @Id
    @GeneratedValue
    @Column(name = "NOL_ID")
    public Long getNolId() {
        return nolId;
    }

    public void setNolId(Long nolId) {
        this.nolId = nolId;
    }

    @Column(name = "NOL_BODY")
    public String getNolBody() {
        return nolBody;
    }

    public void setNolBody(String nolBody) {
        this.nolBody = nolBody;
    }

    @Column(name = "NOL_RETRY")
    public int getNolRetry() {
        return nolRetry;
    }

    public void setNolRetry(int nolRetry) {
        this.nolRetry = nolRetry;
    }

    @Column(name = "NOL_TYPE")
    public String getNolType() {
        return nolType;
    }

    public void setNolType(String nolType) {
        this.nolType = nolType;
    }

    @Column(name = "NOL_STATUS", nullable = false, length = 1)
    public Character getNolStatus() {
        return nolStatus;
    }

    public void setNolStatus(Character nolStatus) {
        this.nolStatus = nolStatus;
    }

    @Column(name = "NOL_REC_STATUS", nullable = false, length = 1)
    public Character getNolRecStatus() {
        return nolRecStatus;
    }

    public void setNolRecStatus(Character nolRecStatus) {
        this.nolRecStatus = nolRecStatus;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull TNotificationLogs o) {
        return 0;
    }
}

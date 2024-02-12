package com.guud.company.library.audit.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.guud.company.library.core.COAbstractEntity;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "T_AUDITLOG")
public class TAuditLog extends COAbstractEntity<TAuditLog> {
    private static final long serialVersionUID = 3668506361378560130L;
    private String audtId;
    private String audtEvent;
    private Timestamp audtTimestamp;
    private String audtAccnid;
    private String audtUid;
    private String audtUname;
    private String audtRemoteIp;
    private String audtReckey;
    private String audtParam1;
    private String audtParam2;
    private String audtParam3;
    private String audtRemarks;

    public TAuditLog() {

    }

    public TAuditLog(String audtId, String audtEvent, Timestamp audtTimestamp, String audtAccnid, String audtUid, String audtUname, String audtRemoteIp, String audtReckey, String audtParam1, String audtParam2, String audtParam3, String audtRemarks) {
        this.audtId = audtId;
        this.audtEvent = audtEvent;
        this.audtTimestamp = audtTimestamp;
        this.audtAccnid = audtAccnid;
        this.audtUid = audtUid;
        this.audtUname = audtUname;
        this.audtRemoteIp = audtRemoteIp;
        this.audtReckey = audtReckey;
        this.audtParam1 = audtParam1;
        this.audtParam2 = audtParam2;
        this.audtParam3 = audtParam3;
        this.audtRemarks = audtRemarks;
    }

    @Override
    public String toString() {
        return "TAuditLog{" +
                "audtId='" + audtId + '\'' +
                ", audtEvent='" + audtEvent + '\'' +
                ", audtTimestamp=" + audtTimestamp +
                ", audtAccnid='" + audtAccnid + '\'' +
                ", audtUid='" + audtUid + '\'' +
                ", audtUname='" + audtUname + '\'' +
                ", audtRemoteIp='" + audtRemoteIp + '\'' +
                ", audtReckey='" + audtReckey + '\'' +
                ", audtParam1='" + audtParam1 + '\'' +
                ", audtParam2='" + audtParam2 + '\'' +
                ", audtParam3='" + audtParam3 + '\'' +
                ", audtRemarks='" + audtRemarks + '\'' +
                '}';
    }

    public void init() {
    }


    private Timestamp convert2Timestamp(Date date) {
        return new Timestamp(this.audtTimestamp != null ? date.getTime() : (new Date()).getTime());
    }

    @Id
    @Column(name = "AUDT_ID", unique = true, nullable = false, length = 35)
    public String getAudtId() {
        return this.audtId;
    }

    public void setAudtId(String audtId) {
        this.audtId = audtId;
    }

    @Column(name = "AUDT_EVENT", nullable = false, length = 25)
    public String getAudtEvent() {
        return this.audtEvent != null ? this.audtEvent.toUpperCase() : this.audtEvent;
    }

    public void setAudtEvent(String audtEvent) {
        this.audtEvent = audtEvent;
    }

    @Column(name = "AUDT_TIMESTAMP", nullable = false, length = 19)
    public Timestamp getAudtTimestamp() {
        return this.audtTimestamp;
    }

    public void setAudtTimestamp(Date audtTimestamp) {
        this.audtTimestamp = this.convert2Timestamp(audtTimestamp);
    }

    public void setAudtTimestamp(Timestamp audtTimestamp) {
        this.audtTimestamp = audtTimestamp;
    }

    @Column(name = "AUDT_ACCNID", length = 35)
    public String getAudtAccnid() {
        return this.audtAccnid;
    }

    public void setAudtAccnid(String audtAccnid) {
        this.audtAccnid = audtAccnid;
    }

    @Column(name = "AUDT_UID", nullable = false, length = 35)
    public String getAudtUid() {
        return audtUid;
    }

    public void setAudtUid(String audtUid) {
        this.audtUid = audtUid;
    }

    @Column(name = "AUDT_UNAME", length = 35)
    public String getAudtUname() {
        return this.audtUname;
    }

    public void setAudtUname(String audtUname) {
        this.audtUname = audtUname;
    }

    @Column(name = "AUDT_REMOTE_IP", length = 35)
    public String getAudtRemoteIp() {
        return this.audtRemoteIp;
    }

    public void setAudtRemoteIp(String audtRemoteIp) {
        this.audtRemoteIp = audtRemoteIp;
    }

    @Column(name = "AUDT_RECKEY", nullable = false, length = 35)
    public String getAudtReckey() {
        return this.audtReckey != null ? this.audtReckey.toUpperCase() : this.audtReckey;
    }

    public void setAudtReckey(String audtReckey) {
        this.audtReckey = audtReckey;
    }

    @Column(name = "AUDT_PARAM1", length = 225)
    public String getAudtParam1() {
        return this.audtParam1 != null ? this.audtParam1.toUpperCase() : this.audtParam1;
    }

    public void setAudtParam1(String audtParam1) {
        this.audtParam1 = audtParam1;
    }

    @Column(name = "AUDT_PARAM2", length = 225)
    public String getAudtParam2() {
        return this.audtParam2 != null ? this.audtParam2.toUpperCase() : this.audtParam2;
    }

    public void setAudtParam2(String audtParam2) {
        this.audtParam2 = audtParam2;
    }

    @Column(name = "AUDT_PARAM3", length = 225)
    public String getAudtParam3() {
        return this.audtParam3 != null ? this.audtParam3.toUpperCase() : this.audtParam3;
    }

    public void setAudtParam3(String audtParam3) {
        this.audtParam3 = audtParam3;
    }

    @Column(name = "AUDT_REMARKS", length = 4096)
    public String getAudtRemarks() {
        return this.audtRemarks != null ? this.audtRemarks.toUpperCase() : this.audtRemarks;
    }

    public void setAudtRemarks(String audtRemarks) {
        this.audtRemarks = audtRemarks;
    }

    @Override
    public int compareTo(@NotNull TAuditLog o) {
        return 0;
    }
}

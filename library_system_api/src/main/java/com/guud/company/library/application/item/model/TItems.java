package com.guud.company.library.application.item.model;

import com.guud.company.library.application.apps.model.TApplication;
import com.guud.company.library.core.COAbstractEntity;
import com.guud.company.library.master.book.model.TBook;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_ITEMS")
public class TItems extends COAbstractEntity<TItems> {

    private String itmID;
    private TApplication application;
    private TBook book;
    private int itmQty;
    private int itmOweQty;
    private String itmReference;
    private Character itmRecStatus;
    private Date itmDtCreate;
    private String itmUidCreate;
    private Date itmDtLupd;
    private String itmUidLupd;

    public TItems() {
    }

    public TItems(String itmID, TApplication application, TBook book, int itmQty, int itmOweQty, String itmReference, Character itmRecStatus, Date itmDtCreate, String itmUidCreate, Date itmDtLupd, String itmUidLupd) {
        this.itmID = itmID;
        this.application = application;
        this.book = book;
        this.itmQty = itmQty;
        this.itmOweQty = itmOweQty;
        this.itmReference = itmReference;
        this.itmRecStatus = itmRecStatus;
        this.itmDtCreate = itmDtCreate;
        this.itmUidCreate = itmUidCreate;
        this.itmDtLupd = itmDtLupd;
        this.itmUidLupd = itmUidLupd;
    }

    @Id
    @Column(name = "ITM_ID")
    public String getItmID() {
        return itmID;
    }

    public void setItmID(String itmID) {
        this.itmID = itmID;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITM_APPLICATION")
    public TApplication getApplication() {
        return application;
    }

    public void setApplication(TApplication application) {
        this.application = application;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITM_BOOK")
    public TBook getBook() {
        return book;
    }

    public void setBook(TBook book) {
        this.book = book;
    }
    @Column(name = "ITM_QtY")
    public int getItmQty() {
        return itmQty;
    }

    public void setItmQty(int itmQty) {
        this.itmQty = itmQty;
    }
    @Column(name = "ITM_OWE_QTY")
    public int getItmOweQty() {
        return itmOweQty;
    }

    public void setItmOweQty(int itmOweQty) {
        this.itmOweQty = itmOweQty;
    }

    @Column(name = "ITM_REFERENCE")
    public String getItmReference() {
        return itmReference;
    }

    public void setItmReference(String itmReference) {
        this.itmReference = itmReference;
    }

    @Column(name = "ITM_REC_STATUS")
    public Character getItmRecStatus() {
        return itmRecStatus;
    }

    public void setItmRecStatus(Character itmRecStatus) {
        this.itmRecStatus = itmRecStatus;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ITM_DT_CREATE", length = 19)
    public Date getItmDtCreate() {
        return itmDtCreate;
    }

    public void setItmDtCreate(Date itmDtCreate) {
        this.itmDtCreate = itmDtCreate;
    }
    @Column(name = "ITM_UID_CREATE", length = 255)
    public String getItmUidCreate() {
        return itmUidCreate;
    }

    public void setItmUidCreate(String itmUidCreate) {
        this.itmUidCreate = itmUidCreate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ITM_DT_LUPD", length = 19)
    public Date getItmDtLupd() {
        return itmDtLupd;
    }

    public void setItmDtLupd(Date itmDtLupd) {
        this.itmDtLupd = itmDtLupd;
    }
    @Column(name = "ITM_UID_LUPD", length = 255)
    public String getItmUidLupd() {
        return itmUidLupd;
    }

    public void setItmUidLupd(String itmUidLupd) {
        this.itmUidLupd = itmUidLupd;
    }


    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull TItems o) {
        return 0;
    }

    public void setBook(int amountLeft) {
    }
}

package com.guud.company.library.application.item.dto;

import com.guud.company.library.application.apps.dto.Application;
import com.guud.company.library.application.item.model.TItems;
import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.book.dto.Book;
import org.jetbrains.annotations.NotNull;
import java.util.Date;

public class Items extends AbstractDTO<Items, TItems> {
    private String itmID;
    private Application application;
    private Book book;
    private int itmQty;
    private int itmOweQty;
    private String itmReference;
    private Character itmRecStatus;
    private Date itmDtCreate;
    private String itmUidCreate;
    private Date itmDtLupd;
    private String itmUidLupd;


    public Items() {
    }

    public Items(String itmID, Application application, Book book, int itmQty, int itmOweQty, String itmReference, Character itmRecStatus, Date itmDtCreate, String itmUidCreate, Date itmDtLupd, String itmUidLupd) {
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

    public Items(TItems entity) {
        super(entity);
    }

    public String getItmID() {
        return itmID;
    }

    public void setItmID(String itmID) {
        this.itmID = itmID;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getItmQty() {
        return itmQty;
    }

    public void setItmQty(int itmQty) {
        this.itmQty = itmQty;
    }

    public int getItmOweQty() {
        return itmOweQty;
    }

    public void setItmOweQty(int itmOweQty) {
        this.itmOweQty = itmOweQty;
    }

    public String getItmReference() {
        return itmReference;
    }

    public void setItmReference(String itmReference) {
        this.itmReference = itmReference;
    }

    public Character getItmRecStatus() {
        return itmRecStatus;
    }

    public void setItmRecStatus(Character itmRecStatus) {
        this.itmRecStatus = itmRecStatus;
    }

    public Date getItmDtCreate() {
        return itmDtCreate;
    }

    public void setItmDtCreate(Date itmDtCreate) {
        this.itmDtCreate = itmDtCreate;
    }

    public String getItmUidCreate() {
        return itmUidCreate;
    }

    public void setItmUidCreate(String itmUidCreate) {
        this.itmUidCreate = itmUidCreate;
    }

    public Date getItmDtLupd() {
        return itmDtLupd;
    }

    public void setItmDtLupd(Date itmDtLupd) {
        this.itmDtLupd = itmDtLupd;
    }

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
    public int compareTo(@NotNull Items o) {
        return 0;
    }
}

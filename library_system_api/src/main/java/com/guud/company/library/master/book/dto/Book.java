package com.guud.company.library.master.book.dto;

import com.guud.company.library.core.AbstractDTO;
import com.guud.company.library.master.book.model.TBook;
import org.jetbrains.annotations.NotNull;
import java.util.Date;

public class Book extends AbstractDTO<Book, TBook> {

    private static final long serialVersionUID = 2867559181555548182L;
    private String bokId;
    private String bokTitle;
    private String bokAuthor;
    private short bokPublicDate;
    private int bokQty;
    private Double bokUnitPrice;
    private byte[] bokCover;
    private String bokBookStatus;
    private Character bokRecStatus;
    private Date bokDtCreate;
    private String bokUidCreate;
    private Date bokDtLupd;
    private String bokUidLupd;

    public Book() {
    }

    public Book(String bokId, String bokTitle, String bokAuthor, short bokPublicDate, int bokQty, Double bokUnitPrice, byte[] bokCover, String bokBookStatus, Character bokRecStatus, Date bokDtCreate, String bokUidCreate, Date bokDtLupd, String bokUidLupd) {
        this.bokId = bokId;
        this.bokTitle = bokTitle;
        this.bokAuthor = bokAuthor;
        this.bokPublicDate = bokPublicDate;
        this.bokQty = bokQty;
        this.bokUnitPrice = bokUnitPrice;
        this.bokCover = bokCover;
        this.bokBookStatus = bokBookStatus;
        this.bokRecStatus = bokRecStatus;
        this.bokDtCreate = bokDtCreate;
        this.bokUidCreate = bokUidCreate;
        this.bokDtLupd = bokDtLupd;
        this.bokUidLupd = bokUidLupd;
    }

    public Book(TBook entity) {
        super(entity);
    }

    public String getBokId() {
        return bokId;
    }

    public void setBokId(String bokId) {
        this.bokId = bokId;
    }

    public String getBokTitle() {
        return bokTitle;
    }

    public void setBokTitle(String bokTitle) {
        this.bokTitle = bokTitle;
    }

    public String getBokAuthor() {
        return bokAuthor;
    }

    public void setBokAuthor(String bokAuthor) {
        this.bokAuthor = bokAuthor;
    }

    public short getBokPublicDate() {
        return bokPublicDate;
    }

    public void setBokPublicDate(short bokPublicDate) {
        this.bokPublicDate = bokPublicDate;
    }

    public int getBokQty() {
        return bokQty;
    }

    public void setBokQty(int bokQty) {
        this.bokQty = bokQty;
    }

    public Double getBokUnitPrice() {
        return bokUnitPrice;
    }

    public void setBokUnitPrice(Double bokUnitPrice) {
        this.bokUnitPrice = bokUnitPrice;
    }

    public byte[] getBokCover() {
        return bokCover;
    }

    public void setBokCover(byte[] bokCover) {
        this.bokCover = bokCover;
    }

    public String getBokBookStatus() {
        return bokBookStatus;
    }

    public void setBokBookStatus(String bokBookStatus) {
        this.bokBookStatus = bokBookStatus;
    }

    public Character getBokRecStatus() {
        return bokRecStatus;
    }

    public void setBokRecStatus(Character bokRecStatus) {
        this.bokRecStatus = bokRecStatus;
    }

    public Date getBokDtCreate() {
        return bokDtCreate;
    }

    public void setBokDtCreate(Date bokDtCreate) {
        this.bokDtCreate = bokDtCreate;
    }

    public String getBokUidCreate() {
        return bokUidCreate;
    }

    public void setBokUidCreate(String bokUidCreate) {
        this.bokUidCreate = bokUidCreate;
    }

    public Date getBokDtLupd() {
        return bokDtLupd;
    }

    public void setBokDtLupd(Date bokDtLupd) {
        this.bokDtLupd = bokDtLupd;
    }

    public String getBokUidLupd() {
        return bokUidLupd;
    }

    public void setBokUidLupd(String bokUidLupd) {
        this.bokUidLupd = bokUidLupd;
    }

    @Override
    public void init() {

    }

    @Override
    public int compareTo(@NotNull Book o) {
        return 0;
    }
}

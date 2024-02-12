package com.guud.company.library.master.book.model;

import com.guud.company.library.core.COAbstractEntity;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_BOOK")
public class TBook extends COAbstractEntity<TBook> {

    private static final long serialVersionUID = 9123770835318834745L;

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

    public TBook() {
    }

    public TBook(String bokId, String bokTitle, String bokAuthor, short bokPublicDate, int bokQty, Double bokUnitPrice, byte[] bokCover, String bokBookStatus, Character bokRecStatus, Date bokDtCreate, String bokUidCreate, Date bokDtLupd, String bokUidLupd) {
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

    @Id
    @Column(name = "BOK_ID", nullable = false, length = 35)
    public String getBokId() {
        return bokId;
    }

    public void setBokId(String bokId) {
        this.bokId = bokId;
    }

    @Column(name = "BOK_TITLE", nullable = false, length = 256)
    public String getBokTitle() {
        return bokTitle;
    }

    public void setBokTitle(String bokTitle) {
        this.bokTitle = bokTitle;
    }

    @Column(name = "BOK_AUTHOR", nullable = false, length = 256)
    public String getBokAuthor() {
        return bokAuthor;
    }

    public void setBokAuthor(String bokAuthor) {
        this.bokAuthor = bokAuthor;
    }

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOK_PUBLIC_DATE", length = 19)
    public short getBokPublicDate() {
        return bokPublicDate;
    }

    public void setBokPublicDate(short bokPublicDate) {
        this.bokPublicDate = bokPublicDate;
    }

    @Column(name = "BOK_QTY")
    @Version
    public int getBokQty() {
        return bokQty;
    }

    public void setBokQty(int bokQty) {
        this.bokQty = bokQty;
    }

    @Column(name = "BOK_UNIT_PRICE")
    public Double getBokUnitPrice() {
        return bokUnitPrice;
    }

    public void setBokUnitPrice(Double bokUnitPrice) {
        this.bokUnitPrice = bokUnitPrice;
    }

    @Column(name = "BOK_COVER")
    public byte[] getBokCover() {
        return bokCover;
    }

    public void setBokCover(byte[] bokCover) {
        this.bokCover = bokCover;
    }

    @Column(name = "BOK_BOOK_STATUS", nullable = false, length = 35)
    public String getBokBookStatus() {
        return bokBookStatus;
    }

    public void setBokBookStatus(String bokBookStatus) {
        this.bokBookStatus = bokBookStatus;
    }

    @Column(name = "BOK_REC_STATUS", length = 1)
    public Character getBokRecStatus() {
        return bokRecStatus;
    }

    public void setBokRecStatus(Character bokRecStatus) {
        this.bokRecStatus = bokRecStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOK_DT_CREATE", length = 19)
    public Date getBokDtCreate() {
        return bokDtCreate;
    }

    public void setBokDtCreate(Date bokDtCreate) {
        this.bokDtCreate = bokDtCreate;
    }

    @Column(name = "BOK_UID_CREATE", length = 256)
    public String getBokUidCreate() {
        return bokUidCreate;
    }

    public void setBokUidCreate(String bokUidCreate) {
        this.bokUidCreate = bokUidCreate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOK_DT_LUPD", length = 19)
    public Date getBokDtLupd() {
        return bokDtLupd;
    }

    public void setBokDtLupd(Date bokDtLupd) {
        this.bokDtLupd = bokDtLupd;
    }

    @Column(name = "BOK_UID_LUPD", length = 256)
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
    public int compareTo(@NotNull TBook o) {
        return 0;
    }
}

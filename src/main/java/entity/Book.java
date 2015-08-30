package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by huangli on 6/18/15.
 */
@Entity
public class Book {
    private int bookId;
    private int userId;
    private String bMid;
    private String delivery;
    private String fTime;
    private String time;
    private String status;
    private String schoolId;

    @Id
    @Column(name = "book_id", nullable = false, insertable = true, updatable = true)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "b_mid", nullable = false, insertable = true, updatable = true, length = 20)
    public String getbMid() {
        return bMid;
    }

    public void setbMid(String bMid) {
        this.bMid = bMid;
    }

    @Basic
    @Column(name = "delivery", nullable = false, insertable = true, updatable = true, length = 20)
    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Basic
    @Column(name = "f_time", nullable = false, insertable = true, updatable = true, length = 20)
    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true, length = 20)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "school_id", nullable = false, insertable = true, updatable = true, length = 20)
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (userId != book.userId) return false;
        if (bMid != null ? !bMid.equals(book.bMid) : book.bMid != null) return false;
        if (delivery != null ? !delivery.equals(book.delivery) : book.delivery != null) return false;
        if (fTime != null ? !fTime.equals(book.fTime) : book.fTime != null) return false;
        if (time != null ? !time.equals(book.time) : book.time != null) return false;
        if (status != null ? !status.equals(book.status) : book.status != null) return false;
        if (schoolId != null ? !schoolId.equals(book.schoolId) : book.schoolId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + userId;
        result = 31 * result + (bMid != null ? bMid.hashCode() : 0);
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (fTime != null ? fTime.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        return result;
    }
}

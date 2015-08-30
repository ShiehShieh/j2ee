package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by huangli on 6/18/15.
 */
@Entity
public class Goods {
    private int goodsId;
    private int userId;
    private String typeId;
    private String startTime;
    private String name;
    private int quality;
    private int soldNum;
    private int bookNum;
    private String tLimit;
    private float price;
    private String status;
    private String nickname;
    private String signature;
    private String upath;
    private String detail;
    private String img;
    private int tokenOff;

    @Id
    @Column(name = "goods_id", nullable = false, insertable = true, updatable = true)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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
    @Column(name = "type_id", nullable = true, insertable = true, updatable = true, length = 6)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "start_time", nullable = false, insertable = true, updatable = true, length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "quality", nullable = false, insertable = true, updatable = true)
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Basic
    @Column(name = "sold_num", nullable = false, insertable = true, updatable = true)
    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    @Basic
    @Column(name = "book_num", nullable = false, insertable = true, updatable = true)
    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    @Basic
    @Column(name = "t_limit", nullable = false, insertable = true, updatable = true, length = 20)
    public String gettLimit() {
        return tLimit;
    }

    public void settLimit(String tLimit) {
        this.tLimit = tLimit;
    }

    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 2)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
    @Column(name = "nickname", nullable = false, insertable = true, updatable = true, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "signature", nullable = false, insertable = true, updatable = true, length = 10)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "upath", nullable = false, insertable = true, updatable = true, length = 255)
    public String getUpath() {
        return upath;
    }

    public void setUpath(String upath) {
        this.upath = upath;
    }

    @Basic
    @Column(name = "detail", nullable = false, insertable = true, updatable = true, length = 16777215)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "img", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "token_off", nullable = false, insertable = true, updatable = true)
    public int getTokenOff() {
        return tokenOff;
    }

    public void setTokenOff(int tokenOff) {
        this.tokenOff = tokenOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (goodsId != goods.goodsId) return false;
        if (userId != goods.userId) return false;
        if (quality != goods.quality) return false;
        if (soldNum != goods.soldNum) return false;
        if (bookNum != goods.bookNum) return false;
        if (Float.compare(goods.price, price) != 0) return false;
        if (tokenOff != goods.tokenOff) return false;
        if (typeId != null ? !typeId.equals(goods.typeId) : goods.typeId != null) return false;
        if (startTime != null ? !startTime.equals(goods.startTime) : goods.startTime != null) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (tLimit != null ? !tLimit.equals(goods.tLimit) : goods.tLimit != null) return false;
        if (status != null ? !status.equals(goods.status) : goods.status != null) return false;
        if (nickname != null ? !nickname.equals(goods.nickname) : goods.nickname != null) return false;
        if (signature != null ? !signature.equals(goods.signature) : goods.signature != null) return false;
        if (upath != null ? !upath.equals(goods.upath) : goods.upath != null) return false;
        if (detail != null ? !detail.equals(goods.detail) : goods.detail != null) return false;
        if (img != null ? !img.equals(goods.img) : goods.img != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId;
        result = 31 * result + userId;
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + quality;
        result = 31 * result + soldNum;
        result = 31 * result + bookNum;
        result = 31 * result + (tLimit != null ? tLimit.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        result = 31 * result + (upath != null ? upath.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + tokenOff;
        return result;
    }
}

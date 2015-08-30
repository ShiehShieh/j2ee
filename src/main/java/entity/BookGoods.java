package entity;

import javax.persistence.*;

/**
 * Created by huangli on 6/18/15.
 */
@Entity
@Table(name = "book_goods", schema = "", catalog = "j2ee")
public class BookGoods {
    private int id;
    private int bookId;
    private int fUserId;
    private int goodsId;
    private String name;
    private String nickname;
    private int num;
    private String path;
    private float price;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_id", nullable = false, insertable = true, updatable = true)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "f_user_id", nullable = false, insertable = true, updatable = true)
    public int getfUserId() {
        return fUserId;
    }

    public void setfUserId(int fUserId) {
        this.fUserId = fUserId;
    }

    @Basic
    @Column(name = "goods_id", nullable = false, insertable = true, updatable = true)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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
    @Column(name = "nickname", nullable = false, insertable = true, updatable = true, length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "num", nullable = false, insertable = true, updatable = true)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "path", nullable = false, insertable = true, updatable = true, length = 255)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 2)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookGoods bookGoods = (BookGoods) o;

        if (id != bookGoods.id) return false;
        if (bookId != bookGoods.bookId) return false;
        if (fUserId != bookGoods.fUserId) return false;
        if (goodsId != bookGoods.goodsId) return false;
        if (num != bookGoods.num) return false;
        if (Float.compare(bookGoods.price, price) != 0) return false;
        if (name != null ? !name.equals(bookGoods.name) : bookGoods.name != null) return false;
        if (nickname != null ? !nickname.equals(bookGoods.nickname) : bookGoods.nickname != null) return false;
        if (path != null ? !path.equals(bookGoods.path) : bookGoods.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        result = 31 * result + fUserId;
        result = 31 * result + goodsId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + num;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}

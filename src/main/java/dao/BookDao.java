package dao;

import entity.Application;
import entity.Book;
import entity.Goods;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by huangli on 6/18/15.
 */
@Repository
public class BookDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public Book findBookById(int id) {
        return em.find(Book.class, id);
    }

    public List<Book> findLatestBook(int start, int amount, String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> c = cb.createQuery(Book.class);
        Root<Book> book = c.from(Book.class);
        c.select(book);
        c.orderBy(cb.desc(book.get("time")));

        TypedQuery<Book> query = em.createQuery(c);
        query.setMaxResults(amount);
        query.setFirstResult(start);
        // query.setHint("status", status);

        List<Book> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }

    public boolean update(int goodid, String typeId, String name, int quality, String detail, String status, String img) {
        Query query = em.createQuery(
                "UPDATE Goods gd SET gd.detail = :detail, gd.img = :img, gd.name = :name," +
                        "gd.quality = :quality, gd.typeId = :typeId, gd.status = :status where gd.goodsId = :goodid");
        query.setParameter("detail", detail);
        query.setParameter("img", img);
        query.setParameter("name", name);
        query.setParameter("quality", quality);
        query.setParameter("typeId", typeId);
        query.setParameter("status", status);
        query.setParameter("goodid", goodid);

        int updateCount = query.executeUpdate();

        return true;
    }

    public boolean change_status(int bookid, String status) {
        Query query = em.createQuery(
                "UPDATE Book bk SET bk.status = :status where bk.bookId = :bookid");
        query.setParameter("status", status);
        query.setParameter("bookid", bookid);

        int updateCount = query.executeUpdate();

        if (status.equals("6")) {
            query = em.createQuery(
                    "UPDATE Book bk SET bk.bMid = :bmid where bk.bookId = :bookid");
            query.setParameter("bmid", bookid);
            query.setParameter("bookid", bookid);
        }

        updateCount = query.executeUpdate();

        return true;
    }
}

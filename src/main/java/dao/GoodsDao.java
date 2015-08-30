package dao;

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
 * Created by huangli on 6/7/15.
 */
@Repository
public class GoodsDao {
    private EntityManager em;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public Goods findGoodsById(int id) {
        return em.find(Goods.class, id);
    }

    public List<Goods> findLatestGoods() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Goods> c = cb.createQuery(Goods.class);
        Root<Goods> goods = c.from(Goods.class);
        c.select(goods);
        c.orderBy(cb.desc(goods.get("startTime")));

        TypedQuery<Goods> query = em.createQuery(c);
        query.setMaxResults(8);

        List<Goods> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }

    public List<Goods> findGoods(String keyWord, String type_id, int page) {
        TypedQuery<Goods> query;
        if (type_id.equals("000000")) {
            query = em.createQuery("select g from Goods g where g.name like :name", Goods.class);
            query.setParameter("name", "%"+keyWord+"%");
        } else {
            query = em.createQuery("select g from Goods g where g.typeId = :typeid and g.name like :name", Goods.class);
            query.setParameter("typeid", type_id);
            query.setParameter("name", "%"+keyWord+"%");
        }

        List<Goods> results = query.getResultList();

        return results;
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

    public boolean updateBookNum(int goodid, int booknum) {
        Query query = em.createQuery(
                "UPDATE Goods gd SET gd.bookNum = :booknum where gd.goodsId = :goodid");
        query.setParameter("booknum", booknum);
        query.setParameter("goodid", goodid);

        int updateCount = query.executeUpdate();

        return true;
    }

    public boolean updateSoldNum(int goodid, int soldnum) {
        Query query = em.createQuery(
                "UPDATE Goods gd SET gd.soldNum = :soldnum where gd.goodsId = :goodid");
        query.setParameter("soldnum", soldnum);
        query.setParameter("goodid", goodid);

        int updateCount = query.executeUpdate();

        // TODO overflow.
        Goods goods = findGoodsById(goodid);
        if (goods.getQuality() == goods.getSoldNum()) {
            change_tokenoff(goodid, 3);
        }

        return true;
    }

    public boolean change_status(int goodid, String status) {
        Query query = em.createQuery(
                "UPDATE Goods gd SET gd.status = :status where gd.goodsId = :goodid");
        query.setParameter("status", status);
        query.setParameter("goodid", goodid);

        int updateCount = query.executeUpdate();

        return true;
    }

    public boolean change_tokenoff(int goodid, int tokenoff) {
        Query query = em.createQuery(
                "UPDATE Goods gd SET gd.tokenOff = :tokenoff where gd.goodsId = :goodid");
        query.setParameter("tokenoff", tokenoff);
        query.setParameter("goodid", goodid);

        int updateCount = query.executeUpdate();

        return true;
    }
}

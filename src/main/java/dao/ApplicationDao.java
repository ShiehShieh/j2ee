package dao;

import com.google.gson.Gson;
import entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import service.GoodsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangli on 6/7/15.
 */
@Repository
public class ApplicationDao {
    private EntityManager em;

    @Autowired
    private GoodsService goodsService;

    @PersistenceContext
    void setEm(EntityManager entityManager) {this.em = entityManager;}

    public Application findApplicationById(int id) {
        return em.find(Application.class, id);
    }

    public List<Application> findLatestApplication(int start, int amount, String status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Application> c = cb.createQuery(Application.class);
        Root<Application> application = c.from(Application.class);
        c.select(application);
        c.orderBy(cb.desc(application.get("time")));

        TypedQuery<Application> query = em.createQuery(c);
        query.setMaxResults(amount);
        query.setFirstResult(start);
        // query.setHint("status", status);

        List<Application> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results;
        }
    }

    public boolean change_status(int appid, String status, String reason) {
        Query query = em.createQuery("UPDATE Application ap SET ap.status = :status, ap.reason = :reason where ap.appId = :appid");
        query.setParameter("appid", appid);
        query.setParameter("status", status);
        query.setParameter("reason", reason);

        int updateCount = query.executeUpdate();

        return true;
    }

    public List<Application> findApplication(String keyWord, String type_id, int page) {
        TypedQuery<Application> query;
        if (type_id.equals("000000")) {
            query = em.createQuery("select g from Goods g where g.name like :name", Application.class);
            query.setParameter("name", "%"+keyWord+"%");
        } else {
            query = em.createQuery("select g from Goods g where g.typeId = :typeid and g.name like :name", Application.class);
            query.setParameter("typeid", type_id);
            query.setParameter("name", "%"+keyWord+"%");
        }

        List<Application> results = query.getResultList();

        return results;
    }

    public ArrayList<Double> get_goods(int appid) {
        TypedQuery<Application> query;
        query = em.createQuery("select app from Application app where app.appId = :appid", Application.class);
        query.setParameter("appid", appid);
        Application result = query.getResultList().get(0);

        String goods = result.getGoodsId();

        Gson gson = new Gson();
        ArrayList<Double> allgoods = gson.fromJson(goods, ArrayList.class);

        return allgoods;
    }
}

package service;

import com.sun.istack.internal.Nullable;
import dao.GoodsDao;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by huangli on 6/7/15.
 */
@Transactional
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods getGoodsById(int id) {
        return goodsDao.findGoodsById(id);
    }

    public @Nullable
    List<Goods> getLatestGoods() {
        return goodsDao.findLatestGoods();
    }

    public List<Goods> getGoods(String keyWord, String typeId, String page) {
        return goodsDao.findGoods(keyWord, typeId, Integer.parseInt(page));
    }

    public boolean update(int goodid, String typeId, String name, int quality, String detail, String status, String img) {
        return goodsDao.update(goodid, typeId, name, quality, detail, status, img);
    }

    public boolean change_status(int goodid, String Status) {
        return goodsDao.change_status(goodid, Status);
    }

    public boolean updateBookNum(int goodid, int booknum) {
        return goodsDao.updateBookNum(goodid, booknum);
    }

    public boolean updateSoldNum(int goodid, int soldnum) {
        return goodsDao.updateSoldNum(goodid, soldnum);
    }

    public boolean change_tokenoff(int goodid, int tokenoff) {
        return goodsDao.change_tokenoff(goodid, tokenoff);
    }
}
package service;

import dao.BookDao;
import dao.BookGoodsDao;
import entity.Book;
import entity.BookGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huangli on 6/18/15.
 */
@Transactional
@Service
public class BookGoodsService {
    @Autowired
    private BookGoodsDao bookGoodsDao;

    public List<BookGoods> findAllBookGoods(int id) {
        return bookGoodsDao.findAllBookGoods(id);
    }

}


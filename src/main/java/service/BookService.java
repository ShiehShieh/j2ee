package service;

import com.sun.istack.internal.Nullable;
import dao.BookDao;
import dao.GoodsDao;
import entity.Book;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huangli on 6/18/15.
 */
@Transactional
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public Book getBookById(int id) {
        return bookDao.findBookById(id);
    }

    public List<Book> findLatestBook(int start, int amount, String status) {
        return bookDao.findLatestBook(start, amount, status);
    }

    public boolean change_status(int bookid, String Status) {
        return bookDao.change_status(bookid, Status);
    }
}

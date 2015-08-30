package service;

import com.sun.istack.internal.Nullable;
import dao.ApplicationDao;
import entity.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangli on 6/7/15.
 */
@Transactional
@Service
public class ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;

    public Application getApplicationById(int id) {
        return applicationDao.findApplicationById(id);
    }

    public @Nullable
    List<Application> getLatestApplication(int start, int amount, String status) {
        return applicationDao.findLatestApplication(start, amount, status);
    }

    public List<Application> getApplication(String keyWord, String typeId, String page) {
        return applicationDao.findApplication(keyWord, typeId, Integer.parseInt(page));
    }

    public boolean change_status(int appid, String status, String reason) {
        return applicationDao.change_status(appid, status, reason);
    }

    public ArrayList<Double> get_goods(int appid) {
        return applicationDao.get_goods(appid);
    }
}

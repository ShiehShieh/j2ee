package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Application;
import entity.Book;
import entity.BookGoods;
import entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.ApplicationService;
import service.BookGoodsService;
import service.BookService;
import service.GoodsService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huangli on 6/6/15.
 */
@Controller
public class HomeController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookGoodsService bookGoodsService;

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping (value = "/home")
    public String home(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        Goods goods = goodsService.getGoodsById(6);
        request.setAttribute("goods", goods);

        return "test";
    }

    @RequestMapping (value = "/adminApplications")
    public String adminApplications(javax.servlet.http.HttpServletRequest request,
                                    javax.servlet.http.HttpServletResponse response) {
        List<Application> applications = applicationService.getLatestApplication(0, 10, "1");
        request.setAttribute("applications", applications);
        request.setAttribute("applicationDetail", null);

        return "adminApplications";
    }

    @RequestMapping (value = "/adminBooks")
    public String panadminBooksel(javax.servlet.http.HttpServletRequest request,
                                  javax.servlet.http.HttpServletResponse response) {
        List<Book> bookList = bookService.findLatestBook(0, 10, "1");
        request.setAttribute("booklist", bookList);

        return "adminBooks";
    }

    @RequestMapping (value = "/adminItem")
    public String adminItem() {
        return "adminItem";
    }

    @RequestMapping (value = "/Admin/Application.json", method = RequestMethod.GET)
    public String applicationlist(javax.servlet.http.HttpServletRequest request,
                                  javax.servlet.http.HttpServletResponse response,
                                  @RequestParam(value = "start") int start,
                                  @RequestParam(value = "count") int count,
                                  @RequestParam(value = "status") String status) {
        List<Application> applications = applicationService.getLatestApplication(start, count, status);
        request.setAttribute("applications", applications);

        return "adminApplications";
    }

    @RequestMapping (value = "/Admin/ApplicationReceive.json", method = RequestMethod.POST)
    public String applicationreceive(javax.servlet.http.HttpServletRequest request,
                                     javax.servlet.http.HttpServletResponse response,
                                     @RequestParam(value = "app_id") int appid) {
        applicationService.change_status(appid, "2", "");
        List<Application> applications = applicationService.getLatestApplication(0, 10, "1");
        request.setAttribute("applications", applications);

        return "adminApplications";
    }

    @RequestMapping (value = "/Admin/ApplicationCancel.json", method = RequestMethod.POST)
    public String applicationcancel(javax.servlet.http.HttpServletRequest request,
                                    javax.servlet.http.HttpServletResponse response,
                                    @RequestParam(value = "app_id") int appid,
                                    @RequestParam(value = "content") String content) {
        applicationService.change_status(appid, "4", content);

        List<Application> applications = applicationService.getLatestApplication(0, 10, "1");
        request.setAttribute("applications", applications);

        return "adminApplications";
    }

    @RequestMapping (value = "/Admin/appDetail.json", method = RequestMethod.GET)
    public String applicationDetail(javax.servlet.http.HttpServletRequest request,
                                    javax.servlet.http.HttpServletResponse response,
                                    @RequestParam(value = "appid") int appid) {
        List<Application> applications = applicationService.getLatestApplication(0, 10, "1");
        request.setAttribute("applications", applications);
        List<Goods> goodsList = new ArrayList<Goods>();
        ArrayList<Double> allgoodsnum = applicationService.get_goods(appid);
        for (Double d : allgoodsnum) {
            goodsList.add(goodsService.getGoodsById(d.intValue()));
        }

        request.setAttribute("goodslist", goodsList);

        return "adminApplications";
    }

    @RequestMapping (value = "/Admin/ApplicationRetrieve.json", method = RequestMethod.POST)
    public String applicationRetrieve(javax.servlet.http.HttpServletRequest request,
                                      javax.servlet.http.HttpServletResponse response,
                                      @RequestParam(value = "app_id") int appid) {
        // goodsService.change_status(22, "2");
        applicationService.change_status(appid, "5", "");
        ArrayList<Double> allgoods = applicationService.get_goods(appid);

        for (int index = 0; index < allgoods.size(); ++index) {
            goodsService.change_status(allgoods.get(index).intValue(), "2");
        }

        List<Application> applications = applicationService.getLatestApplication(0, 10, "1");
        request.setAttribute("applications", applications);

        return "adminApplications";
    }

    @RequestMapping (value = "/Admin/getGoods.json", method = RequestMethod.GET)
    public String getGoods(javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response,
                           @RequestParam(value = "good-id") int goodid) {
        Goods goods = goodsService.getGoodsById(goodid);
        request.setAttribute("goods", goods);

        return "adminItem";
    }

    @RequestMapping (value = "/getbooklist", method = RequestMethod.GET)
    public String getBook(javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response) {
        List<Book> bookList = bookService.findLatestBook(0, 10, "1");
        request.setAttribute("booklist", bookList);

        return "adminBooks";
    }

    @RequestMapping (value = "/getbookdetail", method = RequestMethod.GET)
    public String getBookDetail(javax.servlet.http.HttpServletRequest request,
                                javax.servlet.http.HttpServletResponse response,
                          @RequestParam(value = "id") int bookid) {
        List<Book> bookList = bookService.findLatestBook(0, 10, "1");
        request.setAttribute("booklist", bookList);
        List<BookGoods> bookGoodsList = bookGoodsService.findAllBookGoods(bookid);
        request.setAttribute("bookgoodslist", bookGoodsList);

        return "adminBooks";
    }

    @RequestMapping (value = "/Admin/Goods.json", headers=("content-type=multipart/*"), method = RequestMethod.POST)
    public String goods(@RequestParam(value = "input-item-id") int goodid,
                        @RequestParam(value = "input-item-type") String typeId,
                        @RequestParam(value = "input-item-name") String name,
                        @RequestParam(value = "input-item-quality") int num,
                        @RequestParam(value = "input-item-detail") String detail,
                        @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                        @RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4,
                        @RequestParam("file5") MultipartFile file5) {
        /*
        'mid_id': parseInt(mid_id),
                'type_id': type_id,
                'detail': detail,
                'name': name,
                'num': num
        @RequestParam(value = "mid_id") int goodid, @RequestParam(value = "mid_id") int userId,
        @RequestParam(value = "type_id") String typeId, @RequestParam(value = "mid_id") String startTime,
        @RequestParam(value = "mid_id") String name, @RequestParam(value = "mid_id") int quality,
        @RequestParam(value = "mid_id") int soldNum, @RequestParam(value = "mid_id") int bookNum,
        @RequestParam(value = "mid_id") String tLimit, @RequestParam(value = "mid_id") float price,
        @RequestParam(value = "mid_id") String status, @RequestParam(value = "mid_id") String nickname,
        @RequestParam(value = "mid_id") String signature, @RequestParam(value = "mid_id") String upath,
        @RequestParam(value = "mid_id") String detail, @RequestParam(value = "mid_id") String img,
        @RequestParam(value = "mid_id") int tokenOff
        */
        String fname1 = "/Users/huangli/Documents/IntelliJ/finalproject/tmp/1"+goodid+".jpg";
        String fname2 = "/Users/huangli/Documents/IntelliJ/finalproject/tmp/2"+goodid+".jpg";
        String fname3 = "/Users/huangli/Documents/IntelliJ/finalproject/tmp/3"+goodid+".jpg";
        String fname4 = "/Users/huangli/Documents/IntelliJ/finalproject/tmp/4"+goodid+".jpg";
        String fname5 = "/Users/huangli/Documents/IntelliJ/finalproject/tmp/5"+goodid+".jpg";
        System.out.print(goodid+'\n');
        if (!file1.isEmpty() && !file2.isEmpty() && !file3.isEmpty() &&
                !file4.isEmpty() && !file5.isEmpty()) {
            try {
                byte[] bytes1 = file1.getBytes();
                byte[] bytes2 = file2.getBytes();
                byte[] bytes3 = file3.getBytes();
                byte[] bytes4 = file4.getBytes();
                byte[] bytes5 = file5.getBytes();
                BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(new File(fname1)));
                BufferedOutputStream stream2 = new BufferedOutputStream(new FileOutputStream(new File(fname2)));
                BufferedOutputStream stream3 = new BufferedOutputStream(new FileOutputStream(new File(fname3)));
                BufferedOutputStream stream4 = new BufferedOutputStream(new FileOutputStream(new File(fname4)));
                BufferedOutputStream stream5 = new BufferedOutputStream(new FileOutputStream(new File(fname5)));
                stream1.write(bytes1);
                stream2.write(bytes2);
                stream3.write(bytes3);
                stream4.write(bytes4);
                stream5.write(bytes5);
                String img = "["+"\""+fname1+"\","+"\""+fname2+"\","+"\""+fname3+"\","+"\""+fname4+"\","+"\""+fname5+"\""+"]";
                goodsService.update(goodid, typeId, name, num, detail, "2", img);
                return "adminItem";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
        // return "adminItem";
    }

    @RequestMapping (value = "/waitbook", method = RequestMethod.POST)
    public String waitbook(javax.servlet.http.HttpServletRequest request,
                           javax.servlet.http.HttpServletResponse response,
                           @RequestParam(value = "id") int bookid) {
        bookService.change_status(bookid, "6");
        List<BookGoods> bookGoodsList = bookGoodsService.findAllBookGoods(bookid);
        for (BookGoods bg : bookGoodsList) {
            goodsService.updateBookNum(bg.getBookId(),
                    goodsService.getGoodsById(bg.getGoodsId()).getBookNum()+bg.getNum());
        }
        List<Book> bookList = bookService.findLatestBook(0, 10, "1");
        request.setAttribute("booklist", bookList);

        return "adminBooks";
    }

    @RequestMapping (value = "/cancelbook", method = RequestMethod.POST)
    public String cancelbook(javax.servlet.http.HttpServletRequest request,
                             javax.servlet.http.HttpServletResponse response,
                             @RequestParam(value = "id") int bookid) {
        bookService.change_status(bookid, "3");
        List<BookGoods> bookGoodsList = bookGoodsService.findAllBookGoods(bookid);
        for (BookGoods bg : bookGoodsList) {
            goodsService.updateBookNum(bg.getBookId(),
                    goodsService.getGoodsById(bg.getGoodsId()).getBookNum()-bg.getNum());
        }
        bookService.change_status(bookid, "3");
        List<Book> bookList = bookService.findLatestBook(0, 10, "1");
        request.setAttribute("booklist", bookList);

        return "adminBooks";
    }

    @RequestMapping (value = "/confirmbook", method = RequestMethod.POST)
    public String confirmbook(javax.servlet.http.HttpServletRequest request,
                              javax.servlet.http.HttpServletResponse response,
                              @RequestParam(value = "id") int bookid) {
        bookService.change_status(bookid, "4");
        List<BookGoods> bookGoodsList = bookGoodsService.findAllBookGoods(bookid);
        for (BookGoods bg : bookGoodsList) {
            goodsService.updateSoldNum(bg.getGoodsId(),
                    goodsService.getGoodsById(bg.getGoodsId()).getSoldNum()+bg.getNum());
            goodsService.updateBookNum(bg.getBookId(),
                    goodsService.getGoodsById(bg.getGoodsId()).getBookNum()-bg.getNum());
        }
        List<Book> bookList = bookService.findLatestBook(0, 10, "1");
        request.setAttribute("booklist", bookList);

        return "adminBooks";
    }

}

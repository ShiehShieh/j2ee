<%@ page import="java.awt.print.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.BookGoods" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BookGoods> bookGoodsList = (List<BookGoods>) request.getAttribute("bookgoodslist");
    List<entity.Book> bookList = (List<entity.Book>) request.getAttribute("booklist");
%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="utf-8">
    <title>管理页</title>
    <script src="/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/adminPage.css">
</head>


<body>
    <nav>
        <header>
            Qf Admin
            <a>登出</a>
        </header>

        <ul>
            <li><span>导航栏</span></li>
            <li><a href="/adminBooks">管理订单</a></li>
            <li><a href="/adminApplications">管理申请</a></li>
            <li><a href="/adminItem">物品管理</a></li>
            <li><a>其他</a></li>
        </ul>

    </nav>

    <main>

        <h1>管理订单</h1>

        <div class="flex-grid">
            <div>
                <h2>派送订单</h2>
                <form action="/waitbook" method="post">
                    <input name="id" id="input-book-confirm-id" type="text" placeholder="物品ID" />
                    <input type="submit" value="确定" />
                </form>
            </div>

            <div>
                <h2>取消的订单</h2>
                <form action="/cancelbook" method="post">
                    <input name="id" id="input-user-cancel-id" type="text" placeholder="物品ID" />
                    <input type="submit" value="确定" />
                </form>
            </div>
        </div>

        <div class="flex-grid">
            <div>
                <h2>确认完成订单</h2>
                <form action="/confirmbook" method="post">
                    <input name="id" id="input-confirm-id" type="text" placeholder="物品ID" />
                    <input type="submit" value="确定" />
                </form>
            </div>
        </div>

        <div class="flex-grid">
            <div>
                <table id="application-table">
                    <tr>
                        <td>用户ID</td>
                        <td>时间</td>
                        <td>订单状态</td>
                        <td>订单ID</td>
                        <td>完成时间</td>
                    </tr>
                    <%
                        if (bookList != null) {
                            for (entity.Book bb : bookList)
                            {
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String date1 = sdf1.format(new Date(Integer.parseInt(bb.getTime())*1000L));
                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String date2 = sdf2.format(new Date(Integer.parseInt(bb.getfTime())*1000L));
                                String status = "";
                                switch (bb.getStatus()) {
                                    case "1": {
                                        status = "正在打包";
                                        break;
                                    }
                                    case "2": {
                                        status = "用户取消";
                                        break;
                                    }
                                    case "3": {
                                        status = "官方取消";
                                        break;
                                    }
                                    case "4": {
                                        status = "交易成功";
                                        break;
                                    }
                                    case "5": {
                                        status = "取消审核中";
                                        break;
                                    }
                                    case "6": {
                                        status = "等待收货";
                                        break;
                                    }
                                }
                    %>
                    <tr>
                        <td><%=bb.getUserId()%></td>
                        <td><%=date1%></td>
                        <td><%=status%></td>
                        <td><%=bb.getBookId()%></td>
                        <td><%=date2%></td>
                        <form action="/getbookdetail">
                            <td><button class="check-detail-btn" name="id" value="<%=bb.getBookId()%>">查看详情</button></td>
                        </form>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>

        <div class="flex-grid">
            <div>
                <h2>单个申请详细内容</h2>
                <table id="application-details-table">
                    <tr>
                        <td>订单Id</td>
                        <td>物品ID</td>
                        <td>名称</td>
                        <td>卖家名</td>
                        <td>数量</td>
                        <td>价格</td>
                    </tr>
                    <%
                        if (bookGoodsList != null) {
                            for (BookGoods bg : bookGoodsList)
                            {
                    %>
                    <tr>
                        <td><%=bg.getBookId()%></td>
                        <td><%=bg.getGoodsId()%></td>
                        <td><%=bg.getName()%></td>
                        <td><%=bg.getNickname()%></td>
                        <td><%=bg.getNum()%></td>
                        <td><%=bg.getPrice()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>

    </main>

</body>
</html>

<%@ page import="entity.Goods" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Goods goods = (Goods) request.getAttribute("goods");
%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="utf-8">
    <title>管理页</title>
    <script src="/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/adminPage.css">
    <link rel="stylesheet" href="/css/Huploadify.css">
    <script src="/js/adminUploadItemInfo.js"></script>
    <script src="/js/jquery.Huploadify.js"></script>
</head>
<body>
<nav>
    <header>
        Qf Admin
        <a>登出</a>
    </header>

    <ul>
        <li><span>导航栏</span></li>
        <li><a href="/adminBooks.html">管理订单</a></li>
        <li><a href="/adminApplications.html">管理申请</a></li>
        <li><a href="/adminItem.html">物品管理</a></li>
        <li><a>其他</a></li>
    </ul>

</nav>

<main>

    <h1>物品信息上传</h1>

    <div class="flex-grid">
        <div>
            <h2>基本信息上传</h2>
            <div>
                <form id="form-info-update" action="/Admin/Goods.json" method="POST" enctype="multipart/form-data">
                    <label>物品ID</label>
                    <input id="input-item-id" name="input-item-id" type="text" placeholder="物品ID" />
                    <label>物品类别</label>
                    <select id="input-item-type" name="input-item-type">
                        <option value="100000">交通工具</option>
                        <option value="110000">书籍课本</option>
                        <option value="120000">生活电器</option>
                        <option value="130000">手机数码</option>
                        <option value="140000">体育用具</option>
                        <option value="150000">衣服鞋帽</option>
                        <option value="160000">清风票券</option>
                        <option value="170000">其他宝贝</option>
                        <option value="000000">所有宝贝</option>
                    </select>
                    <br />
                    <label>物品名称</label>
                    <input id="input-item-name" name="input-item-name" type="text" placeholder="物品名称" />
                    <br />
                    <label>物品数量</label>
                    <input id="input-item-quality" name="input-item-quality" type="number" placeholder="物品数量" />

                    <br />
                    <label>物品详细描述</label>
                    <br />
                    <textarea form="form-info-update" placeholder="物品具体描述"
                              id = "input-item-detail" name="input-item-detail"
                              style="margin: 0px; width: 350px; height: 165px;">
                    </textarea>
                    <br />
                    <label>物品图片上传</label>
                    <input type="file" name="file1" id="file1" />
                    <input type="file" name="file2" id="file2" />
                    <input type="file" name="file3" id="file3" />
                    <input type="file" name="file4" id="file4" />
                    <input type="file" name="file5" id="file5" />
                    <button type="submit" name="submit" id="submit">submit</button>
                    <br />
                </form>
            </div>
            <br />
            <br />
            <h2>物品信息查询</h2>

            <div>
                <form action="/Admin/getGoods.json" method="get">
                    <label>填写查询的物品ID：</label>
                    <input tpye="text" name="good-id" placeholder="物品ID" />
                    <input id="input-btn-query" type="submit" value="查询" />
                </form>
                <%
                    if (goods != null) {
                        Gson gson = new Gson();
                        ArrayList<String> allimgs = gson.fromJson(goods.getImg(), ArrayList.class);
                %>
                <div>
                    <label>物品ID：</label> <span id="query-id"><%=goods.getGoodsId()%></span><br />
                    <label>物品名称：</label> <span id="query-name"><%=goods.getName()%></span><br />
                    <label>物品数量：</label> <span id="query-num"><%=goods.getQuality()%></span><br />
                    <label>物品描述：</label> <span id="query-description"><%=goods.getDetail()%></span><br />
                    <%
                        if (allimgs != null) {
                            for (String img : allimgs) {
                    %>
                        <label>物品图片：</label> <span id="query-img"><img src="<%=img%>"></span>
                    <%
                            }
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>

        </div>
    </div>



</main>

</body>
</html>

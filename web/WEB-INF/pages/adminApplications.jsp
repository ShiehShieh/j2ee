<%@ page import="entity.Application" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Goods" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Application> applications = (List<Application>) request.getAttribute("applications");
    Application application1 = (Application) request.getAttribute("applicationDetail");
    List<Goods> goodsList = (List<Goods>) request.getAttribute("goodslist");
%>
<!DOCTYPE html>
<html>
<head lang="zh-CN">
    <meta charset="utf-8">
    <title>管理页</title>
    <script src="/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/adminPage.css">
    <script src="/js/adminApplications.js"></script>
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

        <h1>管理申请</h1>

        <div class="flex-grid">
            <div>
                <h2>申请审核通过</h2>
                <form action="/Admin/ApplicationReceive.json" method="post">
                    <input id="input-appid-reviewed" type="text"
                           onkeyup="return ValidateNumber($(this),value)"
                           name="app_id" placeholder="申请编号" />
                    <input type="submit" value="确定" />
                </form>
            </div>

            <div>
                <h2>取消审核通过</h2>
                <form action="/Admin/ApplicationCancel.json" method="post">
                    <input id="input-appid-cancel" type="text"
                           onkeyup="return ValidateNumber($(this),value)"
                           name="app_id" placeholder="申请编号" />
                    <input id="input-reason-cancel" type="text"
                           name="content" placeholder="取消理由" />
                    <input type="submit" value="确定" />
                </form>
            </div>

            <div>
                <h2>申请物品收取完成</h2>
                <form action="/Admin/ApplicationRetrieve.json" method="post">
                    <input id="input-appid-complete" type="text"
                           onkeyup="return ValidateNumber($(this),value)"
                           name="app_id" placeholder="申请编号" />
                    <input type="submit" value="确定" />
                </form>
            </div>
        </div>

        <div class="flex-grid">
            <div>
                <h2>物品出售申请表</h2>
                <table id="application-table">
                    <tr>
                        <td>申请编号</td>
                        <td>申请人ID</td>
                        <td>时间</td>
                        <td>申请人地址</td>
                        <td>状态</td>
                        <td>查看订单详情</td>
                    </tr>
                
                <%
                    if (applications != null) {
                        for (Application app : applications)
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String date = sdf.format(new Date(Integer.parseInt(app.getTime())*1000L));
                            String status = "";
                            switch (app.getStatus()) {
                                case "1": {
                                    status = "正在审核";
                                    break;
                                }
                                case "2": {
                                    status = "等待收取";
                                    break;
                                }
                                case "3": {
                                    status = "用户取消";
                                    break;
                                }
                                case "4": {
                                    status = "官方取消";
                                    break;
                                }
                                case "5": {
                                    status = "申请完成";
                                    break;
                                }
                            }
                %>
                <tr>
                    <td><%=app.getAppId()%></td>
                    <td><%=app.getUserId()%></td>
                    <td><%=date%></td>
                    <td><%=app.getbNo()%>: <%=app.getNo()%></td>
                    <td><%=status%></td>
                    <form action="/Admin/appDetail.json">
                        <td><button class="check-detail-btn" name="appid" value="<%=app.getAppId()%>">查看详情</button></td>
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
                <h2>单个物品详细内容</h2>
                <table id="application-details-table">
                    <tr>
                        <td>物品ID</td>
                        <td>物品名</td>
                        <td>价格</td>
                        <td>数量</td>
                        <td>买家名</td>
                    </tr>
                    <%
                        if (goodsList != null) {
                            for (Goods gg : goodsList)
                            {
                    %>
                    <tr>
                        <td><%=gg.getGoodsId()%></td>
                        <td><%=gg.getName()%></td>
                        <td><%=gg.getPrice()%></td>
                        <td><%=gg.getQuality()%></td>
                        <td><%=gg.getNickname()%></td>
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

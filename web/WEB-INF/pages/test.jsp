<%@ page import="entity.Goods" %>
<%--
  Created by IntelliJ IDEA.
  User: huangli
  Date: 6/6/15
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  Goods goods = (Goods) request.getAttribute("goods");
%>
<div><%=goods%></div>
test
<form method="POST" enctype="multipart/form-data" action="/upload">
  File to upload: <input type="file" name="file"><br />
  Name: <input type="text" name="name"><br /><br />
  <input type="submit" value="Upload"> Press here to upload the file!
</form>
</body>
</html>

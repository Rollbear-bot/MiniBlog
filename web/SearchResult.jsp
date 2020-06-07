<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/6
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="articleListBean" class="bean.ArticleListBean"/>

<html>
<head>
    <title>SearchResult</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<div id="blank" style="height: 100%; width: 20%; float: left">
    <div style="width:100%;text-align:center">
        <h3>导航栏</h3>
        <a href="HomePage.jsp?login=1">主站</a>
    </div>
</div>
<div id="header" style="height: 10%; width: 80%; float: right">
    <h1>搜索结果</h1>
</div>
<div id="content" style="width: 80%; height: 90%; float: right">
    <%=request.getAttribute("result")%>


</div>

</body>
</html>

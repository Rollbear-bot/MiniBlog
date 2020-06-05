<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="articleBean" class="bean.PostBean"/>

<html>
<head>
    <title>ArticlePage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<%--获得要显示的文章信息--%>
<%
    String article_id = "";
    article_id = (String) request.getParameter("article_id");
    articleBean.setId(Integer.parseInt(article_id));

    String title = articleBean.getTitle();  //获取文章标题
    String text = articleBean.getText();  //获取正文
%>

<div id="container">

    <div id="header" style="border: #6e757a">
        <%--在header中显示当前登录信息--%>
        <%
            String userName = (String) session.getAttribute("userName");
            int userID = 0;
            if(session.getAttribute("userID") != null)
                userID = (int) session.getAttribute("userID");
            if(userName == null)
                out.print("<p>游客，请<a href=\"index.jsp\">登录</a></p>");
            else out.print("<p>当前用户：" + userName + "</p>");
        %>
    </div>

    <div id="menu" style="background-color: #fdf7bb;
    float: left; height: 80%; width: 10%; text-align: center">
        <b>菜单</b>
        <br><a href="HomePage.jsp">HomePage</a>
        <br><a href="PersonalPage.jsp">个人中心</a>
        <br><a href="index.jsp">退出登录</a>
    </div>

    <div id="content" style="height: 80%; float: left;
    background-color: #ececec; width: 90%">
        <%--显示文章--%>
        <h1 style="text-align: center"><%=title%></h1>
        <p><%=text%></p>
    </div>

    <div id="comment" style="background-color: #85a6d8;
    height: 200px; clear: both; text-align: center">
        <p>评论</p>
        <%--todo::评论--%>
    </div>

</div>

</body>
</html>

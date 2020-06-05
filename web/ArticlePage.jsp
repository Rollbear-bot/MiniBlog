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
    <link rel="stylesheet" type="text/css" href="./StaticFiles/han.css">
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

<%--显示当前登录信息--%>
<%
    String userName = (String) session.getAttribute("userName");
    int userID = 0;
    if(session.getAttribute("userID") != null)
        userID = (int) session.getAttribute("userID");
    if(userName == null)
        out.print("<p>游客，请<a href=\"index.jsp\">登录</a></p>");
    else out.print("<p>当前用户：" + userName + "</p>");
%>
<hr>

<%--显示文章--%>
<h1 style="text-align: center"><%=title%></h1>
<p style="text-align: center"><%=text%></p>

<%--todo::评论--%>


</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ArticlePage</title>
</head>
<body>

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

<%--获得要显示的文章信息--%>
<%
//    String title = "";
//    title = (String) request.getAttribute("title");
//    out.print(true);
%>

<%--todo::评论--%>


</body>
</html>

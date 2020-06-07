<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/7
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" class="bean.UserBean"/>
<jsp:useBean id="articleListBean" class="bean.ArticleListBean"/>
<jsp:useBean id="userListBean" class="bean.UserListBean"/>

<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<%--显示提示信息--%>
<%
    //提示信息
    if (request.getAttribute("message") != null){
        out.print("<script>alert(\""
                + request.getAttribute("message") + "\")</script>");
    }
%>

<%--获取当前登录用户（管理员）的信息--%>
<%
    String userName = (String) session.getAttribute("userName");
    int userID = (int) session.getAttribute("userID");
    userBean.setID(userID);
%>

<div id="blank" style="height: 100%; width: 20%; float: left">
    <div style="width:100%;text-align:center">
        <h3>导航栏</h3>
        <a href="HomePage.jsp?login=1">主站</a>
    </div>
</div>

<div id="main">
    <h1>你好，管理员<%=userName%></h1>
    <h2>屏蔽文章</h2>
    <form action="${pageContext.request.contextPath}/BanController"
          method="post">
        <%
            ArrayList<String> lt = articleListBean.getArticleProfile();
            for(int index = 0; index < lt.size(); index+=3){
                out.print("<input type=\"radio\" " +
                        "name=\"article_id\" value=\"" + lt.get(index+2) +
                        "\">" + lt.get(index) + "<br>");
            }
        %>
        <input type="submit" value="屏蔽">
    </form>

<%--    <h2>屏蔽评论</h2>--%>
<%--    <form action="${pageContext.request.contextPath}/BanController"--%>
<%--          method="post">--%>
<%--        <%--%>
<%--            for(int index = 0; index < lt.size(); index+=3){--%>
<%--                out.print("<input type=\"checkbox\" " +--%>
<%--                        "name=\"comment_id\" value=\"" + lt.get(index+2) +--%>
<%--                        "\">" + lt.get(index) + "<br>");--%>
<%--            }--%>
<%--        %>--%>
<%--        <input type="submit" value="屏蔽">--%>
<%--    </form>--%>

    <h2>封禁用户</h2>
    <form action="${pageContext.request.contextPath}/BanController"
          method="post">
        <%
            ArrayList<String> ult = userListBean.getSearchResult("");
            for(int index = 0; index < ult.size(); index+=3){
                out.print("<input type=\"radio\" " +
                        "name=\"user_id\" value=\"" + ult.get(index) +
                        "\">" + ult.get(index+1) + "（注册时间："
                        + ult.get(index+2) + "）<br>");
            }
        %>
        <input type="submit" value="封禁">
    </form>
</div>

</body>
</html>

<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="articleList" class="bean.ArticleListBean"/>
<jsp:useBean id="userList" class="bean.UserListBean"/>
<jsp:useBean id="userBean" class="bean.UserBean"/>

<html>
<head>
    <title>PersonalPage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

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
<div id="header" style="height: 10%; width: 80%; float: right">
    <h1><%=userName%>（权限等级<%=userBean.getPermission()%>）</h1>
</div>

<div id="content" style="width: 80%; height: 90%; float: right">

<%--收藏夹--%>
<h2>收藏夹</h2>
<%
    //从bean里取出当前用户的收藏夹
    ArrayList<String> arrayProfile = articleList.getFavorite(userID);
    String favorite = articleList.toTableLabel(arrayProfile);
%>
<p><%=favorite%></p>


<%--近期浏览--%>
<%
    //从bean里取出当前用户的近期浏览
    String recentBrowse = articleList.toTableLabel(
            articleList.getRecentBrowse(userID));
%>
<h2>近期浏览</h2>
<p><%=recentBrowse%></p>


<%--关注的用户--%>
<h2>关注的用户</h2>
<p><%=userList.toTableLabel(userList.getFollowing(userID), "关注时间")%></p>

<%--如果用户拥有管理员权限，则显示管理员菜单--%>
<h2>管理员菜单</h2>


</div>

</body>
</html>

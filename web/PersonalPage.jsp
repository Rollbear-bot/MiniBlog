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

<html>
<head>
    <title>PersonalPage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<%
    String userName = (String) session.getAttribute("userName");
    int userID = (int) session.getAttribute("userID");
%>

<div id="blank" style="height: 100%; width: 20%; float: left"></div>
<div id="header" style="height: 10%; width: 80%; float: right">
    <h1><%=userName%>，你的用户ID是<%=userID%></h1>
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
<p><%=userList.getFollowing(userID)%></p>

</div>

</body>
</html>

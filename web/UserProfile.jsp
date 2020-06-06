<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/6
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" class="bean.UserBean"/>
<jsp:useBean id="articleListBean" class="bean.ArticleListBean"/>

<%
    if(request.getParameter("userID")==null)
        request.getRequestDispatcher("HomePage.jsp")
                .forward(request, response);
    int userID = Integer.parseInt(request.getParameter("userID"));
    userBean.setID(userID);
%>

<html>
<head>
    <title>UserProfile</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<div id="background">
    <div id="blank" style="height: 100%; width: 20%; float: left"></div>
    <div id="header" style="height: 10%; width: 80%; float: right">
        <h1><%=userBean.getUserName()%></h1>
    </div>
    <div id="content" style="width: 80%; height: 90%; float: right">

        <h2>用户信息</h2>
        <table>
            <tr>
                <td>用户ID</td>
                <td><%=userBean.getUserID()%></td>
            </tr>
            <tr>
                <td>性别</td>
                <td><%=userBean.getGender()%></td>
            </tr>
            <tr>
                <td>电子邮箱</td>
                <td><%=userBean.getEmail()%></td>
            </tr>
            <tr>
                <td>注册日期</td>
                <td><%=userBean.getRegistering_date().toString()%></td>
            </tr>
            <tr>
                <td>权限等级</td>
                <td><%=userBean.getPermission()%></td>
            </tr>
        </table>

        <h2>Ta的收藏</h2>
        <%=articleListBean.toTableLabel(
                articleListBean.getFavorite(userID))%>

    </div>
</div>

</body>
</html>

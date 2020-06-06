<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/5/31
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="articleProfileBean" class="bean.ArticleListBean"/>

<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<%--获取从登录页面转发过来的用户信息--%>
<%
    boolean param_login = request.getParameter("login") != null
            && request.getParameter("login").equals("1");
    boolean att_login = request.getAttribute("login") != null
            && (boolean) request.getAttribute("login");
    boolean login = param_login || att_login;

    String userName = (String) session.getAttribute("userName");
    if(!login){
        session.setAttribute("userID", null);
        session.setAttribute("userName", null);
    }

    String headerMessage = login ? ("你好，" + userName): "你好，游客";

    //提示信息
    if (request.getAttribute("message") != null){
        out.print("<script>alert(\""
                + request.getAttribute("message") + "\")</script>");
    }
%>
<p><%=headerMessage%></p>

<%--从JavaBean获取文章列表并显示--%>
<hr>
<p>文章列表</p>
<%
    ArrayList<String> lt = articleProfileBean.getArticleProfile();
    String table = articleProfileBean.toTableLabel(lt);
    out.print(table);
%>

<%--导航栏--%>
<hr>
<%
    if(login){
        out.print("<p><a href=\"PersonalPage.jsp\">个人主页</a> </p>");
        out.print("<p><a href=\"EditPage.jsp\">发布新文章</a> </p>");
        out.print("<p><a href=\"index.jsp\">退出登录</a> </p>");
    }
    else
        out.print("<p>若要发布文章或关注用户，请<a href=\"index.jsp\">登录</a></p>");
%>

<%--搜索栏--%>
<hr>
<table>
<form action="SearchController" method="post">
    <tr><td>搜索栏</td></tr>
    <tr>
        <td>
            <select name="searchType">
                <option selected>搜索用户</option>
                <option>搜索帖子</option>
            </select>
        </td>
        <td>
            <input type="text" name="text">
        </td>
        <td>
            <input type="submit" value="搜索">
        </td>
    </tr>
</form>
</table>

</body>
</html>

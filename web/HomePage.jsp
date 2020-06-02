<%--
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
</head>
<body>
<script>alert("登陆成功")</script>

<%--获取从登录页面转发过来的用户信息--%>
<%
    int userID = 0;
    userID = (int) session.getAttribute("userID");

    boolean login = request.getAttribute("login") != null && (boolean) request.getAttribute("login");
    String userName = (String) request.getAttribute("userName");

    String headerMessage = login? ("你好，" + userName): "你好，游客";
%>
<p><%=headerMessage%></p>

<hr>
<p>文章列表</p>
<p>
    <jsp:getProperty name="articleProfileBean" property="articleProfile"/>
</p>

<hr>
<p><a href="PersonalPage.jsp">个人主页</a> </p>
<p><a href="EditPage.jsp">编辑文章</a> </p>
<p><a href="index.jsp">退出登录</a> </p>

</body>
</html>

<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="articleList" class="bean.ArticleListBean"/>
<html>
<head>
    <title>PersonalPage</title>
</head>
<body>
<%
    String userName = (String) session.getAttribute("userName");
    int userID = (int) session.getAttribute("userID");
%>
<p><%=userName%>，你的用户ID是<%=userID%></p>
<hr>

<%--收藏夹--%>
<%
    //从bean里取出当前用户的收藏夹
    ArrayList<String> arrayProfile = articleList.getFavorite(userID);
    String table = articleList.toTableLabel(arrayProfile);
%>
<p>收藏夹</p>
<p><%=table%></p>
<hr>

<%--近期浏览--%>

<%--关注的用户--%>

</body>
</html>

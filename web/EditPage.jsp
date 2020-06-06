<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/5/31
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("utf8"); %>

<html>
<head>
    <title>EditPage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<div id="blank" style="height: 100%; width: 20%; float: left"></div>
<div id="header" style="height: 10%; width: 80%; float: right">
    <h1>编辑文章</h1>
</div>
<div id="content" style="width: 80%; height: 90%; float: right">

<table>
    <form action="${pageContext.request.contextPath}/PublishController"
          method="post">
        <tr>
            <td>文章标题</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>正文部分</td>
            <td><input type="text" name="text"></td>
        </tr>
        <tr>
            <td><input type="submit" value="发布"></td>

            <%--使用一个隐藏表单项来向控制器传递用户ID--%>
            <td><input type="hidden" name="userID"
                       value=<%=session.getAttribute("userID")%>></td>
        </tr>
    </form>
</table>

</div>

</body>
</html>

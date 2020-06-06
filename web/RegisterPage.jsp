<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RegisterPage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<div id="blank" style="height: 100%; width: 20%; float: left"></div>
<div id="header" style="height: 10%; width: 80%; float: right">
    <h1>注册</h1>
</div>
<div id="content" style="width: 80%; height: 90%; float: right">

<%--显示提示信息--%>
<%
    //提示信息
    if (request.getAttribute("message") != null){
        out.print("<script>alert(\""
                + request.getAttribute("message") + "\")</script>");
    }
%>

<table>
    <form action="${pageContext.request.contextPath}/RegisterController"
          method="post">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"> </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td><select name="gender">
                <option selected>M</option>
                <option>F</option>
            </select> </td>
        </tr>
        <tr>
            <td>电子邮箱：</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input type="password" name="ensure"></td>
        </tr>
        <tr>
            <td><input type="submit" value="注册"></td>
        </tr>
    </form>
</table>

<hr>
<p>已有账号？<a href="index.jsp">点击登录</a> </p>
<p>或<a href="HomePage.jsp">以游客身份浏览</a> </p>

</div>

</body>
</html>

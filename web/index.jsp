<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/5/30
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
  </head>
  <style>
    table {text-align: center; align-self: center; align-content: center}
    form {text-align: center; align-self: center; align-content: center}
  </style>
  <body>

<%--显示提示信息--%>
<%
  String message = (String) request.getAttribute("message");
  message = (message == null)? "": message;
%>
<p><%=message%></p>
<hr>

<%--  使用表格嵌套表单的方式排版--%>
<div id="blank" style="height: 100%; width: 20%; float: left"></div>
<div id="header" style="height: 10%; width: 80%; float: right">
  <h1>请登录</h1>
</div>
<div id="content" style="width: 80%; height: 90%; float: right">
  <table>
    <form action="${pageContext.request.contextPath}/LoginVerification"
    method="post" name="getLoginInfo">
      <tr>
        <td>邮箱：</td>
        <td><input type="text" name="email"></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="pwd"></td>
      </tr>
      <tr>
        <td><input type="submit" value="登录"></td>
      </tr>
    </form>
  </table>

  <hr>
  <p>还没有账号？<a href="RegisterPage.jsp">注册一个</a> </p>
  <p>或<a href="HomePage.jsp">以游客身份浏览</a> </p>
</div>

  </body>
</html>

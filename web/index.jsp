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
    p {text-align: center}
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
  <p>请登录</p>
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

  <p>还没有账号？<a href="RegisterPage.jsp">注册一个</a> </p>

  </body>
</html>

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
    <title>$Title$</title>
  </head>
  <body>

<%--  使用表格嵌套表单的方式排版--%>
  <p>请登录</p>
  <table>
    <form action="${pageContext.request.contextPath}/LoginVerification"
    method="post" name="getLoginInfo">
      <tr>
        <td>注册邮箱：</td>
        <td><input type="text" name="email"></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="pwd"></td>
      </tr>
      <tr>
        <td><input type="submit"></td>
      </tr>
    </form>
  </table>

  </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PersonalPage</title>
</head>
<body>
<%
    String userName = (String) session.getAttribute("userName");
    int userID = (int) session.getAttribute("userID");
%>

<p>你的用户ID是<%=userID%></p>

</body>
</html>

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
<p>文章列表</p>
<p>
    <jsp:getProperty name="articleProfileBean" property="articleProfile"/>
</p>

</body>
</html>

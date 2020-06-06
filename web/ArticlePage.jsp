<%--
  Created by IntelliJ IDEA.
  User: 13592
  Date: 2020/6/2
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="articleBean" class="bean.PostBean"/>
<jsp:useBean id="userBean" class="bean.UserBean"/>
<jsp:useBean id="articleListBean" class="bean.ArticleListBean"/>

<html>
<head>
    <title>ArticlePage</title>
    <link rel="stylesheet" type="text/css" href="./StaticFiles/vue.css">
</head>
<body>

<%--显示提示信息--%>
<%
    //提示信息
    if (request.getAttribute("message") != null){
        out.print("<script>alert(\""
                + request.getAttribute("message") + "\")</script>");
    }
%>

<%--获得要显示的文章信息--%>
<%
    String article_id = "";
    article_id = (String) request.getParameter("article_id");
    articleBean.setId(Integer.parseInt(article_id));

    String title = articleBean.getTitle();  //获取文章标题
    String text = articleBean.getText();  //获取正文
%>

<%--记录用户浏览信息--%>
<%
    int userID = 0;
    //如果id是null表示访问者是游客
    if (session.getAttribute("userID") != null)
        userID = (int) session.getAttribute("userID");
    userBean.view(userID, Integer.parseInt(article_id));
%>

<div id="container">

    <div id="header" style="border: #6e757a">
        <%--在header中显示当前登录信息--%>
        <%
            String userName = (String) session.getAttribute("userName");
            if(userName == null)
                out.print("<p>游客，请<a href=\"index.jsp\">登录</a></p>");
            else out.print("<p>当前用户：" + userName + "</p>");
        %>
    </div>

    <%--菜单栏--%>
    <div id="menu" style="background-color: #ececec;
    float: left; height: 100%; width: 10%; text-align: center">
        <b>菜单</b>
        <%
            if (userName == null){
                out.print("<br><a href=\"HomePage.jsp\">HomePage</a>");
                out.print("<br><a href=\"index.jsp\">登录</a>");
            }
            else{
                out.print("<br><a href=\"HomePage.jsp?login=1\">HomePage</a>");
                out.print("<br><a href=\"PersonalPage.jsp\">个人中心</a>\n" +
                        "<br><a href=\"index.jsp\">退出登录</a>");
            }
        %>

    </div>

    <div id="title" style="float: left;
    background-color: #e0e0e0; width: 90%">
        <%--显示文章头部--%>
        <h1 style="text-align: center">
            <%=title%>
        </h1>
        <p style="text-align: center">
            作者：<%=articleBean.getAuthorName()%><br>
            发布时间：<%=articleBean.getPublishingDate()%>
        </p>

        <%--收藏文章按钮--%>
        <div style="width:100%;text-align:center">
            <form action="${pageContext.request.contextPath}/FavoriteController"
                  method="post">
                <%--使用隐藏表单项来传递用户ID和当前文章ID--%>
                <input type="hidden" name="userID"
                       value=<%=session.getAttribute("userID")%>>
                <input type="hidden" name="postID"
                        value=<%=article_id%>>
                <input type="submit" value="收藏文章">
            </form>
        </div>
    </div>

    <div id="content" style="height: 80%; width: 70%; float: right">
        <%--显示正文--%>
        <p><%=text%></p>
    </div>

    <div id="comment" style="background-color: #85a6d8;
    height: 200px; clear: both; text-align: center">
        <h2>评论</h2>

        <%--评论输入栏--%>
        <div style="width:100%;text-align:center">
            <form action="${pageContext.request.contextPath}/CommentController"
                  method="post">
                <input type="text" name="text">
                <input type="submit" value="发布评论">
                <%--在隐藏表项中传递当前帖子的ID和用户ID--%>
                <input type="hidden" name="parent_post"
                       value=<%=articleBean.getId()%>>
                <input type="hidden" name="userID"
                       value=<%=userID%>>
            </form>
        </div>

        <%--评论显示栏--%>
        <div style="width:100%;text-align:center">
            <%=articleListBean.getCommentList(articleBean.getId())%>
        </div>
    </div>

</div>

</body>
</html>

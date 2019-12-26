<%@ page import="jpa.model.BlogModel" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20/12/19
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Blog Deletion</title>
</head>
<body>
<%
BlogModel curBlog = (BlogModel)request.getAttribute("BlogObject");%>
<h2>Are you Sure You want to delete the Following Post</h2>
<br>
<br>
<br>
<h2><%=curBlog.getAuthorName()%></h2>
<hr>
<p><%=curBlog.getBlogPost()%></p>
<hr>
<%--@elvariable postId="BlogObject" type="jpa.model.BlogModel"--%>
<form:form action="/post/delete/" modelAttribute="BlogObject" method="post">
    <form:hidden path="authorName"/>
    <form:hidden path="postId"/>
    <form:hidden path="blogPost"/>
    <form:hidden path="blogTitle"/>
    <input type="submit" value="Delete">
</form:form>
</body>
</html>

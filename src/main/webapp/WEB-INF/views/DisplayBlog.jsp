<%@ page import="jpa.model.BlogModel" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 22/12/19
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Blog</title>
</head>
<body>
<%
    BlogModel blogModel =(BlogModel) request.getAttribute("ViewBlog");
%>
<h1><%=blogModel.getBlogTitle()%></h1>
<h2><%=blogModel.getAuthorName()%></h2>
<h2><%=blogModel.getCountry()%></h2>
<p><%=blogModel.getBlogPost()%></p>
</body>
</html>

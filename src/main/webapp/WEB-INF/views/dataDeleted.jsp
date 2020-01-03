<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 22/12/19
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Data Deleted</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp"%>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<p class="text-danger">Post is deleted</p>
${result}
<a href="/post/create">Click here to add Form</a>
<a href="/">Click here for Homepage</a>
</body>
</html>

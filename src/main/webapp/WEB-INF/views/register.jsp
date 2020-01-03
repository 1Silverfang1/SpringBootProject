<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 01/01/20
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp"%>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<div class="text-center mx-auto">
        <form:form action="/register" method="post" modelAttribute="user">
            <label for="name">Name</label>
            <form:input id ="name" path="name"/>
            <br>
            <label for="email" >Email</label>
            <form:input id="email" path="email"/>
            <br>
            <label for="password">Password</label>
            <form:input id="password" path="password"/>
            <br>
            <input type="submit" class="btn btn-success" value="Register">
        </form:form>
</div>
<br><br><br><br>
<hr>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

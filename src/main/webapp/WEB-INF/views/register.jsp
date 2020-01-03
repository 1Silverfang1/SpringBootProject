<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 01/01/20
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
        <form:form action="/register" method="post" modelAttribute="user">
            <label for="name">Name</label>
            <form:input id ="name" path="name"/>
            <label for="email" >Email</label>
            <form:input id="email" path="email"/>
            <label for="password">Password</label>
            <form:input id="password" path="password"/>
            <input type="submit" value="Register">
        </form:form>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 02/01/20
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forgot password</title>
</head>
<body>
<form:form modelAttribute="user" method="post" action="forgotPassword">
    <label for="emailid">Enter your mail Here</label>
    <form:input id="emailid" path="email"/>
    <label for="username">Enter your Username</label>
    <form:input id ="username" path="name"/>
    <input type="submit" value="Send me Verification Link">
</form:form>
</body>
</html>

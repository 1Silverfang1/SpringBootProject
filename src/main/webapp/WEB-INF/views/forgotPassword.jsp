<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<%@include file="/WEB-INF/Layout/header.jsp"%>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<form:form modelAttribute="user" method="post" action="forgotPassword">
    <label for="emailid">Enter your mail Here</label>
    <form:input id="emailid" path="email"/>
    <input type="submit" class="btn btn-success" value="Send me Verification Link">
</form:form>
<br><br><br><br>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

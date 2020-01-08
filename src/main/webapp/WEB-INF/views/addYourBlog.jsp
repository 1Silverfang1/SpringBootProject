<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>

<head>
    <title>Add Blog</title>
</head>

<body>
<%@include file="/WEB-INF/Layout/header.jsp"%>

<security:authorize access="!isAuthenticated()">
    <%
        response.sendRedirect("/login");
    %>
</security:authorize>
<div>
    <%@include file="/WEB-INF/Layout/navbar.jsp" %>
    <p class="text-warning text-center">${msg}</p>
<form:form modelAttribute="yourPost" method="post" cssclass="form-control" action="/post/create">
        <div class="form-group">
            <label for="tile">Enter Blog title</label>
            <input type="hidden" value="<security:authentication property="name"/>" name="author">
            <form:input id="tile" path="title" cssclass="form-control text-center" />
        </div>
        <div class="form-group">
            <label for="content">Enter your Content</label>
        <form:textarea path="content" id ="content" cssclass="form-control text-center"/>
        </div>
        <div class="form-group">
        <form:form  cssclass="form-control text-center" modelAttribute="yourCategory" ><br>
        <form:checkbox path="name"  value="horror" cssStyle="width: available"/> Horror <br>
        <form:checkbox path="name"  value="Comic" cssStyle="width: available"/> Comic <br>
        <form:checkbox path="name" value="SCI-FI" cssclass="form-control"/> SCI-FI <br>
        <form:checkbox path="name" value="Romance" cssclass="form-control"/> Romance <br>
        </div>
            <div class="form-group">

        <input type="submit"  class="btn btn-success" value="Add Post">
            </div>
        </form:form>
        </form:form>
        <hr>
        <hr>
        <%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 28/12/19
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp" %>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<p class="text-warning text-center">
Something Went Wrong Our developer are on it </p>
 <p class="text-warning text-center">${msg}</p>
<c:if test = "${msg==null}">
<p class="text-center text-warning">Page not Found<p>
    </c:if>
<br><br><br>
<hr>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

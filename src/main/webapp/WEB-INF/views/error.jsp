<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 28/12/19
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp" %>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<p class="text-warning text-center">
Something Went Wrong Our developer are on it </p>
 ${msg}
<br><br><br>
<hr>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

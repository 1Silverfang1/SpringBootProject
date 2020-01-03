<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.silverfang.boot.model.UserTable" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp"%>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<%

    String user= (String) request.getAttribute("user");
    if(user==null)
        response.sendRedirect("/");
    System.out.println(user);
%>
<form:form action="/reset-password" method="post">
    <label>
        <input type="password" name="pass" id="password" placeholder="Enter your new pass here" onkeyup='check();'  required>
    </label>
    <label>
        <input type="password" name="confirmPass" id="confirmPassword"  onkeyup='check();' placeholder="Enter your pass again to confirm" required>
    </label>

    <input type="hidden" name="username" value="<%=user%>">
    <span id='message'></span>
    <input type="submit" id="button"  value="Update">
</form:form>
<br><br><br><br>
<hr>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
<script>
    var check = function() {
        if (document.getElementById('password').value ===
            document.getElementById('confirmPassword').value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'matching';
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'not matching';
        }
    }
</script>
</html>

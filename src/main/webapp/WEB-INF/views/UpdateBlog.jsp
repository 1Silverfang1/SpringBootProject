<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.silverfang.boot.model.Post" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="textarea" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 22/12/19
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Update BLog</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp"%>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
 <h2>Update your Blog Here</h2>
 <%--@elvariable postId="BlogObject" type="jpa.model.BlogModel"--%>

 <%
     Post post = (Post) request.getAttribute("myPost");
     String user = post.getUserTable().getName();
     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     String username="";
     String authorities="";
     if (principal instanceof UserDetails) {

         username = ((UserDetails)principal).getUsername();
         authorities= String.valueOf(((UserDetails) principal).getAuthorities());

     } else {

         username= principal.toString();

     }
     if(!authorities.equals("[ROLE_ADMIN]"))
     if(!user.equals(username))
     response.sendRedirect("/");
 %>
<form:form action="/post/edit/${myPost.postId}" modelAttribute="myPost" method="post" >
<form:input path="title"/>
    <form:textarea path="content"/>
    <form:hidden path="postId"/>
    <input type="hidden" value="${myPost.userTable.name}" name="author">
<%--    <form:hidden path="listCategory"/>--%>
    <form:form  cssclass="form-control" modelAttribute="yourCategory" ><br>
        <form:checkbox path="name"  value="horror" cssStyle="width: available"/> Horror <br>
        <form:checkbox path="name"  value="Comic" cssStyle="width: available"/> Comic <br>
        <form:checkbox path="name" value="SCI-FI" cssclass="form-control"/> SCI-FI <br>
        <form:checkbox path="name" value="Romance" cssclass="form-control"/> Romance <br>
        <input type="submit" class="btn btn-success" value="update">
     </form:form>

 </form:form>
<br><br><br>
<hr>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

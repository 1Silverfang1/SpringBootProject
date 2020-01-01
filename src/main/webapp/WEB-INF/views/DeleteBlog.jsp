<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 20/12/19
  Time: 4:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Blog Deletion</title>
</head>
<%
    Post post = (Post) request.getAttribute("deletePost");
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
<body>
<%
Post post1= (Post)request.getAttribute("deletePost");
%>
<h2>Are you Sure You want to delete the Following Post</h2>
<br>
<br>
<br>
<h2><%=post1.getTitle()%></h2>
<hr>
<p><%=post1.getContent()%></p>
<hr>
<%--@elvariable postId="BlogObject" type="jpa.model.BlogModel"--%>
<form:form action="/post/delete/" modelAttribute="deletePost" method="post">
    <form:hidden path="postId"/>
    <input type="submit" value="Delete">
</form:form>
</body>
</html>

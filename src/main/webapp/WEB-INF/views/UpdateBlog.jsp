<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="textarea" uri="http://www.springframework.org/tags/form" %>
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
 <h2>Update your Blog Here</h2>
 <%--@elvariable postId="BlogObject" type="jpa.model.BlogModel"--%>
<form:form action="/post/updateConfirm" modelAttribute="BlogObject" method="post" >
<form:input path="authorName"></form:input>
    <form:input path="blogTitle"></form:input>
<%--    <form:input path="blogPost"></form:input>--%>
    <form:textarea path="blogPost"/>
    <form:hidden path="postId"></form:hidden>
    <form:hidden path="country"/>
     <input type="submit" value="update">
 </form:form>
${BlogId};
</body>
</html>

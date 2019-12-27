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
<form:form action="/post/edit/${myPost.postId}" modelAttribute="myPost" method="post" >
<form:input path="title"/>
    <form:textarea path="content"/>
    <form:hidden path="postId"/>
<%--    <form:hidden path="listCategory"/>--%>
    <form:form  cssclass="form-control" modelAttribute="yourCategory" ><br>
        <form:checkbox path="name"  value="horror" cssStyle="width: available"/> Horror <br>
        <form:checkbox path="name"  value="Comic" cssStyle="width: available"/> Comic <br>
        <form:checkbox path="name" value="SCI-FI" cssclass="form-control"/> SCI-FI <br>
        <form:checkbox path="name" value="Romance" cssclass="form-control"/> Romance <br>
        <input type="submit" value="update">
     </form:form>

 </form:form>
</body>
</html>

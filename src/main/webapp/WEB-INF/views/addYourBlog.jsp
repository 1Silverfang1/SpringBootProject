<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>

<head>
    <title>Add Blog</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp"%>
<div>
    <%@include file="/WEB-INF/Layout/navbar.jsp"%>
<form:form modelAttribute="yourPost" method="post" cssclass="form-control" action="/post/create">
        <div class="form-group">
            <label for="tile">Enter Blog title</label>
            <form:input id="tile" path="title" cssclass="form-control" />
        </div>
        <div class="form-group">
            <label for="content">Enter your Content</label>
        <form:textarea path="content" id ="content" cssclass="form-control"/>
        </div>
        <div class="form-group">
        <form:form  cssclass="form-control" modelAttribute="yourCategory" ><br>
        <form:checkbox path="name"  value="horror" cssStyle="width: available"/> Horror <br>
        <form:checkbox path="name"  value="Comic" cssStyle="width: available"/> Comic <br>
        <form:checkbox path="name" value="SCI-FI" cssclass="form-control"/> SCI-FI <br>
        <form:checkbox path="name" value="Romance" cssclass="form-control"/> Romance <br>
        </div>
            <div class="form-group">

        <input type="submit" value="Add Post">
            </div>
        </form:form>
        </form:form>
        <hr>
        <hr>

<%--        <form>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleFormControlInput1">Email address</label>--%>
<%--                <input type="email"  id="exampleFormControlInput1" placeholder="name@example.com">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleFormControlSelect1">Example select</label>--%>
<%--                <select class="form-control" id="exampleFormControlSelect1">--%>
<%--                    <option>1</option>--%>
<%--                    <option>2</option>--%>
<%--                    <option>3</option>--%>
<%--                    <option>4</option>--%>
<%--                    <option>5</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleFormControlSelect2">Example multiple select</label>--%>
<%--                <select multiple class="form-control" id="exampleFormControlSelect2">--%>
<%--                    <option>1</option>--%>
<%--                    <option>2</option>--%>
<%--                    <option>3</option>--%>
<%--                    <option>4</option>--%>
<%--                    <option>5</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleFormControlTextarea1">Example textarea</label>--%>
<%--                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>--%>
<%--            </div>--%>
<%--        </form>--%>
</body>
</html>

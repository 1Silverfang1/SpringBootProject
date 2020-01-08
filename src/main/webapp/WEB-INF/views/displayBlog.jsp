<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.silverfang.boot.model.Category" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Blog</title>
</head>
<body>
<%@include file="/WEB-INF/Layout/header.jsp" %>
<%@include file="/WEB-INF/Layout/navbar.jsp" %>
<%
    Post post= (Post)request.getAttribute("myPost");
%>

<div class="card" style="width:80%;margin: auto">
    <div class="card-body">
        <h5 class="card-title"><%=post.getTitle()%></h5>
        <h6 class="card-subtitle mb-2 text-muted"><%=post.getUserTable().getName()%></h6>
        <p class="card-text">
              <%=post.getContent()%>
        </p>
        <label for="created">Created At</label><p id="created"><%=post.getCreatedAt()%></p>
        <label for="updated">LastUpdated At</label><p id="updated"><%=post.getUpdatedAt()%></p>
        <br>
        <h6 class="text-success">
            <%
                List<Category> categoryList = post.getListCategory();
                for (Category category : categoryList) {

            %>

            <%=category.getName()%>
            <%
                }
            %>
        </h6>
    </div>
</div>
<br>
<br>
<br>
<br>
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>

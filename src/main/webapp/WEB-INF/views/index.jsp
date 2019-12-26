<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.silverfang.boot.model.UserTable" %>
<%@page isELIgnored="false" %>
<html>
<body>
<head>
    <title>Home</title>
    <%@include file="/WEB-INF/Layout/header.jsp"%>
</head>
<div>
    <%@include file="/WEB-INF/Layout/navbar.jsp"%>
</div>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">AuthorName</th>
        <th scope="col">PostTitle</th>
        <th scope="col">Post Category</th>
        <th scope="col">Post Content</th>
        <th scope="col">Created At</th>
        <th scope="col">Updated At</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <%
            ArrayList<Post> allPost= (ArrayList<Post>) request.getAttribute("allPost");
            for(Post post:allPost)
            {
        %>
        <td>
            <%=post.getContent()%>
        </td>
        <td>
            <%=post.getTitle()%>
        </td>
        <td>
            <%=post.getCreatedAt()%>
        </td>
        <td>
            <%=post.getPublished()%>
        </td>
        <td>
            <%=post.getUpdatedAt()%>
        </td>
        <td>
            <%=post.getAuthorId()%>
        </td>
        <td>
            <%=post.getPostId()%>
        </td>
    </tr>
    </tbody>
    <%}%>
</table>
<br>
<br>
<hr>
</body>
</html>
<%@ page import="com.silverfang.boot.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 27/12/19
  Time: 11:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>


<%@include file="/WEB-INF/Layout/header.jsp"%>
</head>
<body>
<div>
    <%@include file="/WEB-INF/Layout/navbar.jsp"%>
</div>

<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">AuthorName</th>
        <th scope="col">PostTitle</th>
        <th scope="col">Post Content</th>
        <th scope="col">Created At</th>
        <th scope="col">Last Updated At</th>
        <th scope="col">Category</th>
    </tr>
    </thead>
    <tbody>
    <tr>

        <%
            ArrayList<Post> allPost= (ArrayList<Post>) request.getAttribute("allPost");
            int i=0;
            for(Post post:allPost)
            {
                i++;
        %>
        <td scope="row"><%=i%></td>
        <td>
            User
        </td>
        <td>
            <a href="/post/view/<%=post.getPostId()%>"><%=post.getTitle()%></a>
        </td>
        <td>
            <%=post.getContent()%>
        </td>

        <td>
            <%=post.getCreatedAt()%>
        </td>
        <td>
            <%=post.getUpdatedAt()%>
        </td>
        <td>
            <%
                List<Category> categoryList=post.getListCategory();
                for(Category category:categoryList)
                {

            %>

            <%=category.getName()%>
            <%
                }
            %>
        </td>
        <td>
            <a href="/post/edit/<%=post.getPostId()%>">Edit</a>
        </td>
        <td>
            <a href="/post/delete/<%=post.getPostId()%>">Delete</a>
        </td>
    </tr>
    </tbody>
    <%}%>
</table>
<%
System.out.println(request.getAttribute("javax.servlet.forward.query_string"));
%>
</body>
</html>

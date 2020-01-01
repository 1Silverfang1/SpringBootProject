<%@ page import="com.silverfang.boot.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.silverfang.boot.model.Post" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %><%--
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
            <security:authorize access="isAuthenticated()">
                <%
                    String user=post.getUserTable().getName();
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username="";
                    if (principal instanceof UserDetails) {

                        username = ((UserDetails)principal).getUsername();

                    } else {

                        username= principal.toString();

                    }
                    if(user.equals(username))
                    {
                %>
                <a href="/post/edit/<%=post.getPostId()%>">Edit</a>
                <%
                    }
                %>
            </security:authorize>
        </td>
        <td>
            <security:authorize access="isAuthenticated()">
                <%
                    String user=post.getUserTable().getName();
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username="";
                    if (principal instanceof UserDetails) {

                        username = ((UserDetails)principal).getUsername();

                    } else {

                        username= principal.toString();

                    }
                    if(user.equals(username))
                    {
                %>
                <a href="/post/delete/<%=post.getPostId()%>">Delete</a>
                <%
                    }
                %>
            </security:authorize>
        </td>
    </tr>
    </tbody>
    <%}%>
</table>
<%
%>
</body>
</html>

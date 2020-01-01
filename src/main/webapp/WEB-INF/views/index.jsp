<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.silverfang.boot.model.UserTable" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.silverfang.boot.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@page isELIgnored="false" %>
<html>
<body>
<head>
    <title>Home</title>
    <%@include file="/WEB-INF/Layout/header.jsp" %>
</head>
<div>
    <%@include file="/WEB-INF/Layout/navbar.jsp" %>
</div>
<security:authorize access="isAuthenticated()">
    <h2 class="text-success"> Welcome Back,<security:authentication property="name"/></h2>
    <h3 class="text-success">You are given <security:authorize access="hasRole('USER')"> AUTHOR LEVEL PRIVILEGE</security:authorize></h3>
        <security:authorize access="hasRole('ADMIN')"> ADMIN LEVEL PRIVILEGE  </h3>
            <p>With Great Power Comes great Responsibility</p> </security:authorize>
</security:authorize>
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
            ArrayList<Post> allPost = (ArrayList<Post>) request.getAttribute("allPost");
            int i = 0;
            for (Post post : allPost) {
                i++;
        %>
        <td scope="row"><%=i%>
        </td>
        <td>
            <p><%=post.getUserTable().getName()%></p>
        </td>
        <td>
            <a href="/post/view/<%=post.getPostId()%>"><%=post.getTitle()%>
            </a>
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
                List<Category> categoryList = post.getListCategory();
                for (Category category : categoryList) {

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
                   String authorities="";
                   if (principal instanceof UserDetails) {

                        username = ((UserDetails)principal).getUsername();
                       authorities= String.valueOf(((UserDetails) principal).getAuthorities());

                   } else {

                       username= principal.toString();

                   }
                   if(user.equals(username)||authorities.equals("[ROLE_ADMIN]"))                   {
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
                    String authorities="";
                    if (principal instanceof UserDetails) {

                        username = ((UserDetails)principal).getUsername();
                        authorities= String.valueOf(((UserDetails) principal).getAuthorities());

                    } else {

                        username= principal.toString();

                    }
                    if(user.equals(username)||authorities.equals("[ROLE_ADMIN]"))
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
<br>
<br>
<br>
<p>
Page ${CurPage+1} of total ${totalPage+1} </p>
<%

    String queryUrl = (String) request.getAttribute("javax.servlet.forward.query_string");
    System.out.println(queryUrl);
    int curPage = (int) request.getAttribute("CurPage");
    int totalPage = (int) request.getAttribute("totalPage");
    System.out.println("Current Page : " + curPage + " Total Page :" + totalPage);
    if (curPage < totalPage) {
        String newurl = "post?";
        if (queryUrl != null && queryUrl.contains("page")) {
            int newpage = curPage + 1;
            newurl += queryUrl.replaceFirst("page=" + curPage, "page=" + newpage);
            System.out.println(newurl);
%>
<a href="<%=newurl%>">Next Page</a>

<%
} else {
    String newurl1 = "";
    curPage++;
    newurl1 = "post?&";
    if (queryUrl != null) {
        newurl1 += queryUrl;
        newurl1 += "&page=" + curPage;
    }
    else
        newurl1 += "page=" + curPage;
    System.out.println(newurl1);
%>
<a href="<%=newurl1%>">Next Page</a>
<%
    }
%>

<br>
<%
    }
%>
<%
    if (curPage > 0) {
        String prevUrl="";
        if (queryUrl != null && queryUrl.contains("page"))
        {
            System.out.println(queryUrl);
            int pre=curPage-1;
            prevUrl+= queryUrl.replaceFirst("page=" + curPage, "page=" + pre);
            if(!queryUrl.contains("post?"))
            {
                String string="post?";
                string+=prevUrl;
                prevUrl=string;
            }
            System.out.println(prevUrl);
%>
            <a href=<%=prevUrl%>>Previous Page</a> <%
        }



%>

<hr>
<%
    }

%>
</body>
</html>
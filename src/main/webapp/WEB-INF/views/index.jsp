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
<head>
    <title>Home</title>
    <%@include file="/WEB-INF/Layout/header.jsp" %>

</head>
<body>
<div>
    <%@include file="/WEB-INF/Layout/navbar.jsp" %>
</div>
<hr>
<security:authorize access="isAuthenticated()">
    <h2 class="text-success text-center"> Welcome ,<security:authentication property="name"/></h2>
    <h3 class="text-success text-center">You are given <security:authorize
            access="hasRole('USER')"> AUTHOR LEVEL PRIVILEGE</security:authorize></h3>
    <h3 class="text-success text-center"><security:authorize access="hasRole('ADMIN')"> ADMIN LEVEL PRIVILEGE
    <p class="text-success text-center">With Great Power Comes great Responsibility</p></security:authorize>
</security:authorize></h3>

<%
    ArrayList<Post> allPost = (ArrayList<Post>) request.getAttribute("allPost");
    int i = 0;
    for (Post post : allPost) {
        i++;
%>
<div class="card" style="width:80%;margin: auto">
    <div class="card-body">
        <h5 class="card-title"><%=post.getTitle()%></h5>
        <h6 class="card-subtitle mb-2 text-muted"><%=post.getUserTable().getName()%></h6>
        <p class="card-text">
            <%
                String content = post.getContent();
                if (content.length() < 150) {
            %>
            <%=content%>
            <%
            } else {
                String lescontent = content.substring(0, 145);
            %>
            <%=lescontent%>
            <%
                }
            %>
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
        <a class="btn btn-primary" href="/post/view/<%=post.getPostId()%>"<%=post.getTitle()%>>Read More</a>
        <security:authorize access="isAuthenticated()">
            <%
                String user = post.getUserTable().getName();
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String username = "";
                String authorities = "";
                if (principal instanceof UserDetails) {

                    username = ((UserDetails) principal).getUsername();
                    authorities = String.valueOf(((UserDetails) principal).getAuthorities());

                } else {

                    username = principal.toString();

                }
                if (user.equals(username) || authorities.equals("[ROLE_ADMIN]")) {
            %>
            <a class="btn btn-secondary" href="/post/edit/<%=post.getPostId()%>">Edit</a>
            <%
                }
            %>
        </security:authorize>
            <security:authorize access="isAuthenticated()">
                    <%
            String user = post.getUserTable().getName();
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            String authorities = "";
            if (principal instanceof UserDetails) {

                username = ((UserDetails) principal).getUsername();
                authorities = String.valueOf(((UserDetails) principal).getAuthorities());

            } else {

                username = principal.toString();

            }
            if (user.equals(username) || authorities.equals("[ROLE_ADMIN]")) {
        %>
            <a class="btn btn-danger" href="/post/delete/<%=post.getPostId()%>">Delete</a>
                    <%
            }
        %>
            </security:authorize>
    </div>
</div>
<%}%>
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
    } else
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
        String prevUrl = "";
        if (queryUrl != null && queryUrl.contains("page")) {
            System.out.println(queryUrl);
            int pre = curPage - 1;
            prevUrl += queryUrl.replaceFirst("page=" + curPage, "page=" + pre);
            if (!queryUrl.contains("post?")) {
                String string = "post?";
                string += prevUrl;
                prevUrl = string;
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
<%@include file="/WEB-INF/Layout/Footer.jsp" %>
</body>
</html>
<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.silverfang.boot.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Blog</title>
</head>
<body>
<%
    Post post= (Post)request.getAttribute("myPost");
%>
<h1><%=post.getTitle()%></h1>
<h2>User</h2>
<%
    List<Category> list= post.getListCategory();
    for (Category category:list)
    {
%>
<h3><%=category.getName()%>
</h3>
<%
    }
%>
<p><%=post.getContent()%></p>
<br>
<p>Last updated at : <%=post.getUpdatedAt()%></p>
<br>
<p>Last created at : <%=post.getCreatedAt()%></p>
<br>
</body>
</html>

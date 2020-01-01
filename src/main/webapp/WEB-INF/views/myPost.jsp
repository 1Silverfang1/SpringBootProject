<%@ page import="java.util.ArrayList" %>
<%@ page import="com.silverfang.boot.model.Post" %>
<%@ page import="com.silverfang.boot.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Post</title>
</head>
<body>
<%
    ArrayList<Post> posts= (ArrayList<Post>) request.getAttribute("allPost");
    for(Post post:posts)
    {
%>
    <p><%=post.getTitle()%></p>
<p><%=post.getContent()%></p>
<p><%=post.getUserTable()%></p>
<p><%=post.getUpdatedAt()%></p>
    <%
        for(Category category: post.getListCategory())
        {
    %>
        <p><%=category.getName()%></p>
    <%
        }
    %>
<br>
<%
    }
%>
</body>
</html>

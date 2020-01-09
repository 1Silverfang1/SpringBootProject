<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Spring Boot WebSocket Chat Application | CalliCoder</title>


</head>
<link rel="stylesheet" href="/css/main.css">
<body
      style="background-position: center; background-repeat: no-repeat; background-size: cover;">
<noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>
<%
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username="";
//    String authorities="";
    if (principal instanceof UserDetails) {

        username = ((UserDetails)principal).getUsername();

    } else {

        username= principal.toString();
        System.out.println("ekse");
    }
%>
<div id="username-page">
    <div class="username-page-container">
        <form id="usernameForm" name="usernameForm">
            <div class="form-group">
                <input type="hidden" id="name" value="<%=username%>"
                       autocomplete="off" class="form-control" />
            </div>
            <div class="form-group">
                <button type="submit" class="accent username-submit">Comment Now</button>
            </div>
        </form>
    </div>
</div>

<div id="chat-page" class="hidden">
    <div class="chat-container">
        <div class="chat-header">
            <h2>CommentBox</h2>
        </div>
        <div class="connecting">Connecting...</div>
        <ul id="messageArea">

        </ul>
        <form id="messageForm" name="messageForm" nameForm="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..."
                           autocomplete="off" class="form-control" />
                    <button type="submit" class="primary">Comment</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="js/main.js"></script>
</html>
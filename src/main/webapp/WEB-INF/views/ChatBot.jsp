<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/main.css">
<div id="chat-page">
    <div class="chat-container">
        <div class="chat-header">
            <h2>Chat Messanger</h2>
        </div>
        <div class="connecting">Connecting...</div>
        <ul id="messageArea">

        </ul>
        <form id="messageForm" name="messageForm" nameForm="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <label for="message"></label><input type="text" id="message" placeholder="Type a message..."
                                                        autocomplete="off" class="form-control" />
                    <button type="submit" class="primary">Comment</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/js/main.js"></script>
</html>
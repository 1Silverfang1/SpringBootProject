'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var postId = document.querySelector('#curPost');

var stompClient = null;
var username = null;
window.onload = function () {
  var text = prompt("Enter your username", "");
  connect(true,text);
};

function connect(event,uname) {
  username = Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5);
  if(uname !=null & uname.length>0)
    username=uname;
  var socket = new SockJS('/chatapp');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, onConnected, onError);
  event.preventDefault();
}

function onConnected() {
  stompClient.subscribe('/topic/public', onMessageReceived);

  // Tell your username to the server
  stompClient.send("/app/chat.register",
      {},
      JSON.stringify({
        sender: username,
        type: 'JOIN',
        content: username + " joined chat"
      })
  )

  connectingElement.classList.add('hidden');
}

function onError(error) {
  connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
  connectingElement.style.color = 'red';
}

function send(event) {

  var messageContent = messageInput.value.trim();
  if (messageContent && stompClient) {
    var chatMessage = {
      sender: username,
      content: messageInput.value,
      type: 'CHAT',
      id: 1
    };

    stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
    messageInput.value = '';
  }
  event.preventDefault();
}

function onMessageReceived(payload) {
  var message = JSON.parse(payload.body);

  var messageElement = document.createElement('li');

  messageElement.classList.add('chat-message');
  var usernameElement = document.createElement('span');
  var usernameText = document.createTextNode(message.sender);
  usernameElement.appendChild(usernameText);
  messageElement.appendChild(usernameElement);

  var textElement = document.createElement('p');
  var messageText = document.createTextNode(message.content);
  textElement.appendChild(messageText);

  messageElement.appendChild(textElement);
  if (message.type === 'CHAT') {
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
  } else {
    messageElement.removeChild(usernameElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
  }
}

messageForm.addEventListener('submit', send, true)

package com.silverfang.boot.websocket;

import com.silverfang.boot.interfaces.PostServiceInterface;
import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.Comment;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.CommentRepository;
import com.silverfang.boot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private PostServiceInterface postServiceInterface;
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("post id is "+ chatMessage.getId());
        UserTable userTable=userServiceInterface.findUser(chatMessage.getSender());
        Post post = postServiceInterface.getPostById(chatMessage.getId());
        Comment comment= new Comment();
        comment.setComment(chatMessage.getContent());
        if(userTable==null)
        {
            System.out.println("asccccccccc");
            return chatMessage;
        }
        comment.setUsers(userTable);
        post.getCommentList().add(comment);
        userTable.getCommentList().add(comment);
        userServiceInterface.saveUser(userTable);
        comment.setPostComment(post);
        commentRepository.save(comment);
        postServiceInterface.savePost(post);
        return chatMessage;
    }

}

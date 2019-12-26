package com.silverfang.bootblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
@Service
public class BlogService implements ServiceInterface {
    @Autowired
    private UserRepository userRepository;
    public List<Post> getPost()
    {
     return  userRepository.findAll();
    }
    public void savePost(Post post)
    {
        userRepository.save(post);
    }
}

package com.silverfang.bootblog;

import java.util.List;

public interface ServiceInterface {
    public List<Post> getPost();
    public void savePost(Post post);
}

package com.silverfang.boot.service;

import com.silverfang.boot.interfaces.CategoryServiceInterface;
import com.silverfang.boot.interfaces.PostServiceInterface;
import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private final PostServiceInterface postServiceInterface;
    private final UserServiceInterface userServiceInterface;
    private final CategoryServiceInterface categoryServiceInterface;
    private Logger LOGGER= LoggerFactory.getLogger(BlogService.class);

    public BlogService(PostServiceInterface postServiceInterface, UserServiceInterface userServiceInterface, CategoryServiceInterface categoryServiceInterface) {
        this.postServiceInterface = postServiceInterface;
        this.userServiceInterface = userServiceInterface;
        this.categoryServiceInterface = categoryServiceInterface;
    }

    public SimpleMailMessage sendMailNow(UserTable userTable, TokenOTP confirmationToken, String url) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userTable.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("vaibhavrawat00000@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + url + confirmationToken.getConfirmationToken());
        return mailMessage;
    }
    public String getLoggedInUserDetails(String userDetails)
    {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        String authorities="";
        if (principal instanceof UserDetails) {

            username = ((UserDetails)principal).getUsername();
            authorities= String.valueOf(((UserDetails) principal).getAuthorities());

        } else {

            username= principal.toString();

        }
        if(userDetails.equals("username"))
        return  username;
        else
            return authorities;
    }

    public Category getSingleCategory(String cateGoryName) {
        return categoryServiceInterface.getSingleCategory(cateGoryName);
    }

    public void saveMyBlog(Post myPost, Category myCategory, String authorName) {
        if (myCategory.getName() != null) {
            String[] cat = myCategory.getName().split(",");
            for (String category : cat) {
                Category category1 = categoryServiceInterface.getSingleCategory(category);
                myPost.getListCategory().add(category1);
            }
        }
        UserTable userTable = userServiceInterface.getUser(authorName);
        myPost.setUserTable(userTable);
        postServiceInterface.savePost(myPost);
    }

    public Post viewMyPost(int id) {
        return postServiceInterface.getPostById(id);
    }

    public List<Post> getMyPost(Pageable pageRequest) {
        return postServiceInterface.getPost(pageRequest);
    }

    public void deleteBlog(Post post) {
        postServiceInterface.deleteBlog(post);
    }

    public List<Post> filterPost(Category filter, Pageable pageable) {
        return postServiceInterface.filterPost(filter, pageable);
    }

    private List<Post> search(String key, Pageable pageable) {
        return postServiceInterface.searchInPost(key, pageable);
    }

    private Category getCategory(String key) {
        return categoryServiceInterface.getCategory(key);
    }

    public List<Post> searchMyBlog(String key, Pageable pageable) {
        List<Post> postList = search(key, pageable);
        try {

            Category category = getCategory(key);
            System.out.println(category.getName());
            List<Post> allPostList = filterPost(category, Pageable.unpaged());
            for (Post post : allPostList) {
                if (!postList.contains(post)) {
                    postList.add(post);
                }
            }
        } catch (Exception e) {
         LOGGER.warn("Not a valid category, It doesnot exist in the database");
        }
        return postList;
    }

}

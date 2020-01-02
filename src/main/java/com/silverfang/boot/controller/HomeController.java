package com.silverfang.boot.controller;

import com.silverfang.boot.interfaces.PostServiceInterface;
import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.PostRepository;
import com.silverfang.boot.repository.TokenRepository;
import com.silverfang.boot.security.MyUserDetailService;
import com.silverfang.boot.service.BlogService;
import com.silverfang.boot.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class HomeController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private PostRepository postServiceInterface;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MailService mailService;
    @GetMapping("/register")
    public ModelAndView getRegistered()
    {
        UserTable userTable = new UserTable();
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user",userTable);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView saveAuthor(@ModelAttribute("user") UserTable userTable)
    {
        userTable.setRoles("AUTHOR");
        userTable.setEnable(false);
        myUserDetailService.save(userTable);
        TokenOTP confirmationToken = new TokenOTP(userTable);
        tokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userTable.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("vaibhavrawat00000@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        ModelAndView modelAndView= new ModelAndView("DataSucess");
        mailService.sendEmail(mailMessage);
        modelAndView.addObject("emailId", userTable.getEmail());
        modelAndView.setViewName("Registered");
        return modelAndView;
    }
    @GetMapping("/myPost")
    public ModelAndView getMyPost( @RequestParam(defaultValue = "0" ,required = false,name = "page") int page ,
                                   @RequestParam(defaultValue = "4" ,required = false, name = "pageSize") int pageSize,
                                   @RequestParam(defaultValue = "title",required = false, name = "sortBy") String title)
    {
        ModelAndView modelAndView= new ModelAndView("index");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {

            username = ((UserDetails)principal).getUsername();

        } else {

            username= principal.toString();

        }

        UserTable userTable = userServiceInterface.getUser(username);
        Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
        ArrayList<Post> postList= (ArrayList<Post>) postServiceInterface.findPostByUserTable(userTable,pageable);
        int total=postList.size()/pageSize;
        modelAndView.addObject("allPost",postList);
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",total);
        return modelAndView;

    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        TokenOTP token = tokenRepository.findByConfirmationToken(confirmationToken);
        Date date = new Date();
        if(date.getTime() - token.getCreatedDate().getTime() >10*60*60)
        if(token != null)
        {

            UserTable user = userServiceInterface.getUser(token.getUser().getName());
            user.setEnable(true);
            userServiceInterface.saveUser(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

@GetMapping({"/post","/"})
    public ModelAndView sortHomePageByTitle(@RequestParam(defaultValue = "title",required = false, name = "sortBy") String title,
                                            @RequestParam(defaultValue = "0" ,required = false,name = "page") int page ,
                                            @RequestParam(defaultValue = "4" ,required = false, name = "pageSize") int pageSize,
                                            @RequestParam(defaultValue = "" ,required = false, name = "filterBy") String filter,
                                            @RequestParam(defaultValue = "", required = false ,name = "key")String key) {
    ModelAndView modelAndView = new ModelAndView("index");
    if (!key.equals(""))
    {
        Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
        List<Post> postList=blogService.searchMyBlog(key,pageable);
        List<Post> postList2= new ArrayList<>();
        if(!filter.equals(""))
        {

            Category category= blogService.getSingleCategory(filter);
            System.out.println(category.getName());
            List<Post> postList1= blogService.filterPost(category,Pageable.unpaged());
            System.out.println(postList1.size());
        for(Post post:postList)
        {
            if(postList1.contains(post))
            {
                postList2.add(post);
            }
        }
            ModelAndView modelAndView1 = new ModelAndView("searchedresult");
            modelAndView1.addObject("allPost", postList2);
            return modelAndView1;
        }
        ModelAndView modelAndView1 = new ModelAndView("searchedresult");
        modelAndView1.addObject("allPost", postList);
        return modelAndView1;
    }
    if(!filter.equals(""))
    {
        System.out.println("sadasd");
        Category category= blogService.getSingleCategory(filter);
        Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
        if(title.equals("updatedAt"))
            pageable= PageRequest.of(page,pageSize,Sort.by(title).descending());
        List<Post> postList= blogService.filterPost(category,pageable);
        List<Post> list=blogService.filterPost(category,Pageable.unpaged());
        modelAndView.addObject("allPost",postList);
        int total=list.size()/pageSize;
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",total);
        return  modelAndView;
    }
    Pageable paging;
    paging = PageRequest.of(page, pageSize,Sort.by(title));
    if(title.equals("updatedAt")) {
        paging = PageRequest.of(page, pageSize, Sort.by(title).descending());
    }
    List<Post> pagenationPost= blogService.getMyPost(paging);
   List<Post>  allPost= blogService.getMyPost(Pageable.unpaged());
    int total=allPost.size()/pageSize;
    modelAndView.addObject("CurPage",page);
    modelAndView.addObject("totalPage",total);
    modelAndView.addObject("allPost",pagenationPost);
    return  modelAndView;
}
}

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

     Logger LOGGER= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserServiceInterface userServiceInterface;
    @Autowired
    private PostServiceInterface postServiceInterface;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MailService mailService;
    @GetMapping("/forgotPassword")
    public ModelAndView restMyPass()
    {
        UserTable userTable= new UserTable();
        LOGGER.info("forgot password method invoked");
        String username=blogService.getLoggedInUserDetails("username");
        if(!username.equals("anonymousUser"))
        {
            ModelAndView modelAndView= new ModelAndView("error");
            modelAndView.addObject("msg","You are already logged in");
            return  modelAndView;
        }
        ModelAndView modelAndView=new ModelAndView("forgotPassword");
        modelAndView.addObject("user",userTable);
        return  modelAndView;
    }
    @PostMapping("/forgotPassword")
    public ModelAndView resetMyPass(@ModelAttribute("user") UserTable user)
    {
        LOGGER.info("forgot password method invoked and Details have been sent by the user");

        ModelAndView modelAndView= new ModelAndView("accountVerified");
        System.out.println(user.getEmail());
          UserTable  userTable= userServiceInterface.findUserTableByEmail(user.getEmail());
            if(userTable==null)
        {
            LOGGER.warn("User dont exist : inside resetMyPass function");
            modelAndView.setViewName("error");
            modelAndView.addObject("msg","user don't exist");
            LOGGER.info("Redirecting to Error page");
            return  modelAndView;
        }

        TokenOTP newToken=tokenRepository.findByUser(userTable);
        LOGGER.info("Token is being searched for the user");
        TokenOTP tokenOTP= new TokenOTP(userTable);
        if(newToken!=null)
        {
            newToken.setConfirmationToken(tokenOTP.getConfirmationToken());
            newToken.setCreatedDate(tokenOTP.getCreatedDate());
            LOGGER.info("Token is being overwritten");
            LOGGER.info("Token is being now being loaded in the message link");
            SimpleMailMessage mailMessage= blogService.sendMailNow(userTable,newToken,"http://13.235.190.211:8080/reset-password?token=");
            tokenRepository.save(newToken);
            LOGGER.info("Token is saved in the database");
            mailService.sendEmail(mailMessage);
            LOGGER.info("Mail is sent to the email");
            return  modelAndView;
        }
        LOGGER.warn("Token for user not exist so generating token");
        SimpleMailMessage mailMessage= blogService.sendMailNow(userTable,tokenOTP,"http://13.235.190.211:8080/reset-password?token=");
        tokenRepository.save(tokenOTP);
        LOGGER.info("Token is saved in the database :outside if");
        mailService.sendEmail(mailMessage);
        LOGGER.info("Mail is sent to the email");
        return  modelAndView;
    }
    @RequestMapping(value="/reset-password", method= RequestMethod.GET)
    public  ModelAndView resetMyPass(@RequestParam("token")String confirmationToken)
    {
     ModelAndView modelAndView= new ModelAndView("reset");
     modelAndView.addObject("resetPass",true);
        LOGGER.info("finding the token in the database");
        TokenOTP token = tokenRepository.findByConfirmationToken(confirmationToken);
        Date date = new Date();
        if(token != null) {
            LOGGER.info("Token exist in the database");
            if (date.getTime() - token.getCreatedDate().getTime() < 360000) {
                LOGGER.info("Token exist in the database and is valid");
                modelAndView.addObject("user", token.getUser().getName());
                modelAndView.addObject("token",confirmationToken);
                return modelAndView;

            } else {
                LOGGER.warn("Token is expired");
                modelAndView.addObject("msg", "The link is invalid or broken! or is expired");
                modelAndView.setViewName("error");
                return modelAndView;
            }
        }
        LOGGER.warn("Token dont exist in the database");
        modelAndView.addObject("msg","The link is expired generate again by registering");
        modelAndView.setViewName("error");
        LOGGER.info("going back to the register page");
        return  modelAndView;

    }
    @PostMapping("/reset-password")
    public  ModelAndView savePassword(@RequestParam("username") String username,@RequestParam("pass") String password,
    @RequestParam("token") String token) {

        LOGGER.info("in reset password function ");
        LOGGER.info("searching for the user name in the database");
        TokenOTP tokenOTP = tokenRepository.findByConfirmationToken(token);
        UserTable userTable1;
        if (tokenOTP != null) {
            userTable1 = tokenOTP.getUser();
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("msg", "Cannot validate your token");
            return modelAndView;
        }
        UserTable userTable = userServiceInterface.getUser(username);
        if (userTable.getName().equals(userTable1.getName())) {
            try {
                LOGGER.info("Setting new password");
                userTable.setPassword(password);

            } catch (Exception e) {
                LOGGER.error("Error in setting the password for the user ");
                LOGGER.error("Are you sure user exist in the database error is :" + e.getMessage());
                LOGGER.error("Error Stacktrace ", e);
                ModelAndView modelAndView = new ModelAndView("error");
                modelAndView.addObject("msg", "Error in setting the new password");
                return modelAndView;
            }

        try {
            myUserDetailService.save(userTable);
        } catch (Exception e) {
            LOGGER.error("Error saving the data in the database with error message " + e.getMessage());
            LOGGER.error("stacktrace is ", e);
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("msg", "Error in saving the new data in the database");
            return modelAndView;
        }
    }
        else
        {
            ModelAndView modelAndView= new ModelAndView("error");
            modelAndView.addObject("msg","Cannot verify it is you who have generated the token");
            return  modelAndView;
        }

        LOGGER.info("Saving Modified User in the database");
        return  new ModelAndView("dataSuccess");
    }
    @GetMapping("/register")
    public ModelAndView getRegistered()
    {
        String username=blogService.getLoggedInUserDetails("username");
        if (!username.equals("anonymousUser"))
        {
            return new ModelAndView("/");
        }
        UserTable userTable=new UserTable();
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user",userTable);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView saveAuthor(@ModelAttribute("user") UserTable userTable) throws IOException {
        LOGGER.info("Inside SaveAuthor function");
        userTable.setRoles("AUTHOR");
        userTable.setEnable(false);
        LOGGER.info("Finding if user already exist in the database");
        UserTable userTable1= userServiceInterface.getUser(userTable.getName());
        UserTable userTable2=userServiceInterface.findUserTableByEmail(userTable.getEmail());
        if(userTable1==null) {
            if(userTable2==null) {
                myUserDetailService.save(userTable);
                LOGGER.info("Saving new Author");
            }
            else
            {
                LOGGER.info("Author already exist in the database");
                ModelAndView modelAndView= new ModelAndView("error");
                modelAndView.addObject("msg","Email already exist");
                return  modelAndView;
            }
        }
        else
        {

            if(userTable1.isEnable()) {
                LOGGER.info("Author exits and is already verified");
            ModelAndView modelAndView= new ModelAndView("error");
            modelAndView.addObject("msg","username Already exist and is verified");
            return  modelAndView;
            }
            LOGGER.info("Author exist in the database but is not verified so searching for the token in the database");
            TokenOTP confirmationToken = new TokenOTP(userTable1);
            TokenOTP newToken=tokenRepository.findByUser(userTable1);
            if(newToken==null)
            {
                LOGGER.info("Token do not exist in the database so now generating and saving a new one ");
                tokenRepository.save(confirmationToken);
                LOGGER.info("Token saved and now generating mail");
                ModelAndView modelAndView= new ModelAndView();
                SimpleMailMessage mailMessage = blogService.sendMailNow(userTable1,confirmationToken,"http://13.235.190.211:8080/confirm-account?token=");
                LOGGER.info("Mail generated and sending mail now");
                mailService.sendEmail(mailMessage);
                LOGGER.info("Mail sent");
                modelAndView.addObject("emailId", userTable.getEmail());
                modelAndView.setViewName("registered");
                return modelAndView;
            }
            else
            {
                LOGGER.info("Token already exist in the database so overwriting the previous token");
                newToken.setCreatedDate(confirmationToken.getCreatedDate());
                newToken.setConfirmationToken(confirmationToken.getConfirmationToken());
                tokenRepository.save(newToken);
                LOGGER.info("Token overwritten and saved");
                SimpleMailMessage mailMessage = blogService.sendMailNow(userTable,newToken,"http://13.235.190.211:8080/confirm-account?token=");
                LOGGER.info("Mail generated and now sending");
                mailService.sendEmail(mailMessage);
                LOGGER.info("mail sent");
                return new ModelAndView("registered");
            }

        }
        TokenOTP tokenOTP= new TokenOTP(userTable);
        tokenRepository.save(tokenOTP);
        LOGGER.info("new user is saved and in being registered ");
        SimpleMailMessage mailMessage = blogService.sendMailNow(userTable,tokenOTP,"http://13.235.190.211:8080/confirm-account?token=");
        LOGGER.info("Mail generated and now sending");
        try {
            mailService.sendEmail(mailMessage);
        }
        catch (Exception e)
        {
            LOGGER.error("Error while sending the mail",e);
            ModelAndView modelAndView= new ModelAndView("error");
            modelAndView.addObject("msg","Error while sending the mail");
            return  modelAndView;
        }
        LOGGER.info("mail sent");
        return new ModelAndView("registered");
    }
    @GetMapping("/myPost")
    public ModelAndView getMyPost( @RequestParam(defaultValue = "0" ,required = false,name = "page") int page ,
                                   @RequestParam(defaultValue = "4" ,required = false, name = "pageSize") int pageSize,
                                   @RequestParam(defaultValue = "title",required = false, name = "sortBy") String title)
    {
        LOGGER.info("Inside my post");
        ModelAndView modelAndView= new ModelAndView("index");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=blogService.getLoggedInUserDetails("username");
        UserTable userTable;
        try {
            userTable = userServiceInterface.getUser(username);
        }
        catch (Exception e)
        {
            LOGGER.error("error while searching the username in the database",e);
            modelAndView.setViewName("error");
            modelAndView.addObject("msg","Error in getting your Post Try again later");
            return modelAndView;
        }
         Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
        ArrayList<Post> postList=new ArrayList<>();
        try {
            postList = (ArrayList<Post>) postServiceInterface.findPostByUser(userTable, pageable);
        }
        catch (Exception e)
        {
            LOGGER.error("Error while searching for the post by the user");
            return new ModelAndView("error");
        }
        int total=postList.size()/pageSize;
        modelAndView.addObject("allPost",postList);
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",total);
        return modelAndView;

    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        LOGGER.info("inside confirm account function");
        TokenOTP token = tokenRepository.findByConfirmationToken(confirmationToken);
        Date date = new Date();
        if(token != null)
        if(date.getTime() - token.getCreatedDate().getTime() <360000)
        {
            LOGGER.info("Token exist in the database and is valid");
            try {
                UserTable user = userServiceInterface.getUser(token.getUser().getName());
                user.setEnable(true);
                userServiceInterface.saveUser(user);
            }
            catch (Exception e)
            {
                LOGGER.error("error in saving data to the database" ,e);
                ModelAndView modelAndView1= new ModelAndView("error");
                modelAndView1.addObject("msg","Error while confirming the account");
                return modelAndView1;
            }
            LOGGER.info("User is verified and saved");
            modelAndView.setViewName("accountVerified");
            return  modelAndView;
        }
        else
        {
            LOGGER.warn("Token is expired or not present");
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        modelAndView.addObject("msg","token not valid or is expired Register again to generate a new one");
        modelAndView.setViewName("error");
        return modelAndView;
    }

@GetMapping({"/post","/"})
public ModelAndView sortHomePageByTitle(@RequestParam(defaultValue = "title",required = false, name = "sortBy") String title,
                                            @RequestParam(defaultValue = "0" ,required = false,name = "page") int page ,
                                            @RequestParam(defaultValue = "" ,required = false, name = "filterBy") String filter,
                                            @RequestParam(defaultValue = "", required = false ,name = "key")String key,
                                            @RequestParam(defaultValue = "4",required = false,name = "pageSize")int pageSize ) {
    LOGGER.info("Inside homepage function");
        ModelAndView modelAndView = new ModelAndView("index");
        if (!key.equals(""))
        {
            LOGGER.info("User has used a search function now searching key -> "+ key);
            Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
            List<Post> postList=blogService.searchMyBlog(key,pageable);//    modelAndView.addObject("allPost",allPost);
            List<Post> postList2= new ArrayList<>();
            if(!filter.equals("")) {
                LOGGER.info("User has used a search function with filter now filtering result with  -> " + filter);
                Category category =null;
                try {
                    category = blogService.getSingleCategory(filter);
                } catch (Exception e)
                {
                    LOGGER.error(e.getMessage());
                    LOGGER.error("error stacktrace",e);
                    LOGGER.error("Category searched failed for the category ->" + filter);
                    modelAndView.setViewName("error");
                    modelAndView.addObject("msg","The Category doesn't exist in the database");
                    return  modelAndView;
                }
                LOGGER.info("Category exits in the database");
                List<Post> postList1= blogService.filterPost(category,Pageable.unpaged());
                for(Post post:postList)
                {
                    if(postList1.contains(post))
                    {
                        postList2.add(post);
                    }
                }
                ModelAndView modelAndView1 = new ModelAndView("searchedResult");
                modelAndView1.addObject("allPost", postList2);
                return modelAndView1;
            }
            LOGGER.info("Filter is not applied on the searched data");
            ModelAndView modelAndView1 = new ModelAndView("searchedResult");
            modelAndView1.addObject("allPost", postList);
            return modelAndView1;
        }
        if(!filter.equals("")) {
            LOGGER.info("User has used a filter function");
            Category category = null;
            try {
                category = blogService.getSingleCategory(filter);
            } catch (Exception e)
            {
                LOGGER.error(e.getMessage());
                LOGGER.error("error stacktrace",e);
                LOGGER.error("Category searched failed for the category ->" + filter);
                modelAndView.setViewName("error");
                modelAndView.addObject("msg","The Category doesn't exist in the database");
                return  modelAndView;
            }

            Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
            if(title.equals("updatedAt"))
                pageable= PageRequest.of(page,pageSize,Sort.by(title).descending());
            List<Post> postList= blogService.filterPost(category,pageable);
            List<Post> list=blogService.filterPost(category,Pageable.unpaged());
            modelAndView.addObject("allPost",postList);
            int total=list.size()/4;
            modelAndView.addObject("CurPage",page);
            modelAndView.addObject("totalPage",total);
            return  modelAndView;
        }
        Pageable paging;
        paging = PageRequest.of(page, pageSize,Sort.by(title));
        if(title.equals("updatedAt")) {
            paging = PageRequest.of(page, pageSize, Sort.by(title).descending());
        }
        List<Post> paginationPost= blogService.getMyPost(paging);
        List<Post>  allPost= blogService.getMyPost(Pageable.unpaged());
        int total=allPost.size()/4;
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",total);
        modelAndView.addObject("allPost",paginationPost);
        return  modelAndView;
    }
}

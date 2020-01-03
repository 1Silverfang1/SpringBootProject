package com.silverfang.boot.controller;

import com.silverfang.boot.BootblogApplication;
import com.silverfang.boot.interfaces.PostServiceInterface;
import com.silverfang.boot.interfaces.UserServiceInterface;
import com.silverfang.boot.model.Category;
import com.silverfang.boot.model.Post;
import com.silverfang.boot.model.TokenOTP;
import com.silverfang.boot.model.UserTable;
import com.silverfang.boot.repository.CategoryRepository;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class HomeController {

     Logger LOGGER= LoggerFactory.getLogger(HomeController.class);
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
    @Autowired
    private CategoryRepository c;
    @GetMapping("/forgotPassword")
    public ModelAndView restMyPass()
    {
        LOGGER.info("forgot passwword method invoked");
        ModelAndView modelAndView=new ModelAndView("fogotpassword");
        UserTable userTable= new UserTable();
        modelAndView.addObject("user",userTable);
        return  modelAndView;
    }
    @PostMapping("/forgotPassword")
    public ModelAndView resetMyPass(@ModelAttribute("user") UserTable userTable)
    {
        LOGGER.info("forgot passwword method invoked and Details have been sent by the user");

        ModelAndView modelAndView= new ModelAndView("accountVerified");
        UserTable userTable1= userServiceInterface.getUser(userTable.getName());
        if(userTable1==null)
        {
            LOGGER.warn("User dont exist : inside resetMyPass function");
            modelAndView.setViewName("error");
            modelAndView.addObject("msg","user don't exist");
            LOGGER.info("Redirecting to Error page");
            return  modelAndView;
        }

        TokenOTP newToken=tokenRepository.findByUser(userTable1);
        LOGGER.info("Token is being searched for the user");
        TokenOTP tokenOTP= new TokenOTP(userTable1);
        if(newToken!=null)
        {
            newToken.setConfirmationToken(tokenOTP.getConfirmationToken());
            newToken.setCreatedDate(tokenOTP.getCreatedDate());
            LOGGER.info("Token is being overwritten");
            LOGGER.info("Token is being now being loaded in the message link");
            SimpleMailMessage mailMessage= blogService.sendMailNow(userTable1,newToken,"https://spring-boot-project.herokuapp.com//reset-password?token=");
            tokenRepository.save(newToken);
            LOGGER.info("Token is saved in the database");
            mailService.sendEmail(mailMessage);
            LOGGER.info("Mail is sent to the email");
            return  modelAndView;
        }
        LOGGER.warn("Token for user not exist so generating token");
        SimpleMailMessage mailMessage= blogService.sendMailNow(userTable1,tokenOTP,"https://spring-boot-project.herokuapp.com//reset-password?token=");
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
        System.out.println(confirmationToken);
        TokenOTP token = tokenRepository.findByConfirmationToken(confirmationToken);
        Date date = new Date();
        if(token != null) {
            LOGGER.info("Token exist in the database");
            if (date.getTime() - token.getCreatedDate().getTime() < 360000) {
                LOGGER.info("Token exist in the database and is valid");
                modelAndView.addObject("user", token.getUser().getName());
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
    public  ModelAndView savePassword(@RequestParam("username") String username,@RequestParam("pass") String password)
    {

        LOGGER.info("in reset password function ");
        LOGGER.info("searching for the user name in the database");
        UserTable userTable= userServiceInterface.getUser(username);
        userTable.setPassword(password);
        myUserDetailService.save(userTable);
        return  new ModelAndView("DataSucess");
    }
    @GetMapping("/register")
    public ModelAndView getRegistered()
    {
        UserTable userTable = new UserTable();
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user",userTable);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView saveAuthor(@ModelAttribute("user") UserTable userTable) throws IOException {
        userTable.setRoles("AUTHOR");
        userTable.setEnable(false);
        UserTable userTable1= userServiceInterface.getUser(userTable.getName());
        UserTable userTable2=userServiceInterface.findUserTableByEmail(userTable.getEmail());
        if(userTable1==null) {
            if(userTable2==null)
            myUserDetailService.save(userTable);
            else
            {
                ModelAndView modelAndView= new ModelAndView("error");
                modelAndView.addObject("msg","Email already exist");
                return  modelAndView;
            }
        }
        else
        {

            if(userTable1.isEnable()) {

            ModelAndView modelAndView= new ModelAndView("error");
            modelAndView.addObject("msg","username Already exist and is verified");
            return  modelAndView;
            }
            TokenOTP confirmationToken = new TokenOTP(userTable1);
            TokenOTP newToken=tokenRepository.findByUser(userTable1);
            if(newToken==null)
            {
                tokenRepository.save(confirmationToken);
                ModelAndView modelAndView= new ModelAndView();
                SimpleMailMessage mailMessage = blogService.sendMailNow(userTable1,confirmationToken,"https://spring-boot-project.herokuapp.com/confirm-account?token=");
                mailService.sendEmail(mailMessage);
                modelAndView.addObject("emailId", userTable.getEmail());
                modelAndView.setViewName("Registered");
                return modelAndView;
            }
            else
            {
                SimpleMailMessage mailMessage = blogService.sendMailNow(userTable,newToken,"https://spring-boot-project.herokuapp.com/confirm-account?token=");
                mailService.sendEmail(mailMessage);
                ModelAndView modelAndView= new ModelAndView("Registered");
                return  modelAndView;
            }

        }
        TokenOTP confirmationToken = new TokenOTP(userTable);
        tokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = blogService.sendMailNow(userTable,confirmationToken,"https://spring-boot-project.herokuapp.com/confirm-account?token=");
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
        LOGGER.error("error occured just kididng");
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
        if(token != null)
        if(date.getTime() - token.getCreatedDate().getTime() <360000)
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
                                            @RequestParam(defaultValue = "" ,required = false, name = "filterBy") String filter,
                                            @RequestParam(defaultValue = "", required = false ,name = "key")String key,
                                            @RequestParam(defaultValue = "4",required = false,name = "pageSize")int pageSize ) {
//        Category category1= new Category("horror");
//        c.save(category1);
//    Category category2= new Category("Romance");
//    Category category3= new Category("SCI-FI");
//    Category category4= new Category("Comic");
//        c.save(category2);
//        c.save(category3);
//        c.save(category4);
        ModelAndView modelAndView = new ModelAndView("index");
        if (!key.equals(""))
        {
            Pageable pageable= PageRequest.of(page,pageSize,Sort.by(title));
            List<Post> postList=blogService.searchMyBlog(key,pageable);//    modelAndView.addObject("allPost",allPost);
            List<Post> postList2= new ArrayList<>();
            if(!filter.equals(""))
            {

                Category category= blogService.getSingleCategory(filter);
                System.out.println(category.getName());
//            Pageable pageable1= PageRequest.of(page,4,Sort.by(title));
                List<Post> postList1= blogService.filterPost(category,Pageable.unpaged());
                System.out.println(postList1.size());
                for(Post post:postList)
                {
                    System.out.println(post.getListCategory().get(0).getName());
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
        List<Post> pagenationPost= blogService.getMyPost(paging);
        List<Post>  allPost= blogService.getMyPost(Pageable.unpaged());
        int total=allPost.size()/4;
        modelAndView.addObject("CurPage",page);
        modelAndView.addObject("totalPage",total);
        modelAndView.addObject("allPost",pagenationPost);
        return  modelAndView;
    }
}

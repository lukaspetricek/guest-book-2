package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.CommentServiceImpl;
import com.brights.guestbook2.service.PostServiceImpl;
import com.brights.guestbook2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "unused"})
@Controller
public class MainPageController {
    private UserServiceImpl userService;
    private PostServiceImpl postService;
    private CommentServiceImpl commentService;


    @Autowired
    public MainPageController(UserServiceImpl userService, PostServiceImpl postService, CommentServiceImpl commentService){
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }
    @GetMapping("*")
    public String userLost() {
        return"redirect:/";
    }

    @GetMapping("/")
    public String guestPage(Model model){
        model.addAttribute("listOfPosts", postService.getAllPosts());
        model.addAttribute("listOfComments", commentService.getAllComments());
        return "preview";
    }
    @GetMapping("/index")
    public String homePage(Principal userPrincipal,Model model){
        model.addAttribute("user", userPrincipal);
        model.addAttribute("comment",new Comment());
        model.addAttribute("listOfPosts", postService.getAllPosts());
        model.addAttribute("listOfComments", commentService.getAllComments());
        User user = userService.getUserByUsername(userPrincipal.getName());
        if (user.isAdmin()){
            return "indexAdmin";
        }else {
        return "index";
        }
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}

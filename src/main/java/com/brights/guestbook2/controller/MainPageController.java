package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.service.PostServiceImpl;
import com.brights.guestbook2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@SuppressWarnings("FieldMayBeFinal")
@Controller
public class MainPageController {
    private UserServiceImpl userService;
    private PostServiceImpl postService;
    @Autowired
    public MainPageController(UserServiceImpl userService, PostServiceImpl postService){
        this.userService = userService;
        this.postService = postService;
    }
    @GetMapping("*")
    public String userLost() {
        return"redirect:/";
    }

    @GetMapping("/")
    public String guestPage(Model model){
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "preview";
    }
    @GetMapping("/index")
    public String homePage(Model model){
        model.addAttribute("listOfAllUser", userService.getAllUsers());
        model.addAttribute("comment",new Comment());
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}

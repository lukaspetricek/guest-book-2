package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.repository.PostRepository;
import com.brights.guestbook2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainPageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    public MainPageController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @GetMapping("*")
    public String userLost() {
        return"redirect:/";
    }

    @GetMapping("/")
    public String guestPage(Model model){
        model.addAttribute("listOfPosts", postRepository.findAll());
        return "preview";
    }
    @GetMapping("/index")
    public String homePage(Model model){
        model.addAttribute("listOfAllUser", userRepository.findAll());
        model.addAttribute("comment",new Comment());
        model.addAttribute("listOfPosts", postRepository.findAll());
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}

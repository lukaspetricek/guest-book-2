package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class MainPageController {
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
    public String guestPage(@ModelAttribute("sessionUser") User sessionUser, Model model){
        model.addAttribute("listOfPosts", postRepository.findAll());
        return "preview";
    }
    @GetMapping("/index")
    public String homePage(@ModelAttribute("sessionUser") User sessionUser, Model model){
        Comment comment = new Comment();
        model.addAttribute("comment",comment);
        model.addAttribute("listOfPosts", postRepository.findAll());
        return "index";
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
}

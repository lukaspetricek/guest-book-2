package com.brights.guestbook2.controller;

import com.brights.guestbook2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @Autowired
    private PostService postService;

    @Autowired
    public MainPageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("*")
    public String homePage(Model model){
        model.addAttribute("listOfPosts", postService.getAllPosts());
        return "index";
    }
}

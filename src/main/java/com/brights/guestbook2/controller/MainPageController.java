package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainPageController {
    @Autowired
    private PostService postService;

    @Autowired
    public MainPageController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("*")
    public String userLost(Model model) {
        return"redirect:/";
    }

    @GetMapping("/")
    public String homePage(Model model){
        User user = new User();
        model.addAttribute("listOfPosts", postService.getAllPosts());
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/comment/{id}")
    public String commentPost(@PathVariable(value = "id") Long id, Model model) {
        Comment comment = new Comment();
        model.addAttribute("post",postService.getPostById(id));
        model.addAttribute("comment",comment);
        return "post/comments";
    }
}

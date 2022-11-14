package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainPageController {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
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
    public String homePage(@ModelAttribute("sessionUser") User sessionUser, Model model){
        model.addAttribute("listOfPosts", postRepository.findAll());
        return "index";
    }
    @GetMapping("/comment/{id}")
    public String commentPost(@PathVariable(value = "id") Long id, Model model) {
        Comment comment = new Comment();
        model.addAttribute("post",postRepository.findById(id));
        model.addAttribute("comment",comment);
        return "post/comments";
    }
}

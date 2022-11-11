package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Post;
import com.brights.guestbook2.service.PostService;
import com.brights.guestbook2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@SuppressWarnings("unused")
@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post/add")
    public String addPost(Model model){
        model.addAttribute("post", new Post());
        return "post/new";
    }

    @PostMapping("/post/checkPost")
    public String checkPost(@Valid Post post, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        postService.savePost(post);
        return "redirect:/";
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}

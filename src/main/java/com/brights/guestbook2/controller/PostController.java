package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.Post;
import com.brights.guestbook2.service.PostService;
import com.brights.guestbook2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String checkPost(@Valid Post post, BindingResult bindingResult,Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", principal);
        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        postService.savePost(post);
        return "redirect:/";
    }
    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long id) {
        postService.deletePostById(id);
        return "redirect:/";
    }

    @PostMapping("/post/addComment/{id}")
    public String addComment(@PathVariable(value = "id") Long id, @Valid Comment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "post/comments";
        }
        postService.getPostById(id).addComment(comment);
        return "redirect:/post/comments";
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}

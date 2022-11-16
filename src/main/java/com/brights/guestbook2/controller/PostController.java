package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.Post;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.PostServiceImpl;
import com.brights.guestbook2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@SuppressWarnings({"unused", "FieldMayBeFinal"})
@Controller
public class PostController {
    private PostServiceImpl postService;
    private UserServiceImpl userService;



    @Autowired
    public PostController(PostServiceImpl postService, UserServiceImpl userService) {

        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post/add")
    public String addPost(Model model){
        model.addAttribute("post", new Post());
        return "post/new";
    }

    @PostMapping("/post/checkPost{username}")
    public String checkPost(@PathVariable(value = "username") String username, @Valid Post post, BindingResult bindingResult){
        User user = userService.getUserByUsername(username);
        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        post.setUser(user);
        postService.savePost(post);
        return "redirect:/index";
    }
    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long id) {
        postService.deletePostById(id);
        return "redirect:/index";
    }

    @PostMapping("/post/addComment/{id}")
    public String addComment(@PathVariable(value = "id") Long id,Principal userPrincipal, @Valid Comment comment){
        comment.setUser(userService.getUserByUsername(userPrincipal.getName()));
        postService.getPostById(id).addComment(comment);
        return "redirect:/index";
    }
}

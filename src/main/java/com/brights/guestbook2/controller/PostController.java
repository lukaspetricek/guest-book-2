package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.Post;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.CommentServiceImpl;
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
    private CommentServiceImpl commentService;



    @Autowired
    public PostController(PostServiceImpl postService, UserServiceImpl userService, CommentServiceImpl commentService) {

        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/post/add")
    public String addPost(Model model){
        model.addAttribute("post", new Post());
        return "post/new";
    }
    @GetMapping("/post/edit/{id}")
    public String editPost(@PathVariable Long id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post/edit";
    }
    @GetMapping("/comment/edit/{id}")
    public String editComment(@PathVariable Long id, Model model){
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "post/editComment";
    }
    @PostMapping("/comment/edit/")
    public String saveEditedComment(@Valid Comment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "post/editComment";
        }
        commentService.saveComment(comment);
        return "redirect:/index";
    }
    @GetMapping("/users/admin/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id){
        commentService.deleteCommentById(id);
        return "redirect:/index";
    }

    @PostMapping("/post/editPost")
    public String saveEditedPost(@Valid Post post, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        postService.savePost(post);
        return "redirect:/index";
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
        comment.setPost(postService.getPostById(id));
        comment.setUser(userService.getUserByUsername(userPrincipal.getName()));
        postService.getPostById(id).addComment(comment);
        commentService.saveComment(comment);
        return "redirect:/index";
    }
}

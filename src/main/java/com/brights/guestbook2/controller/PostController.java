package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.model.Post;
import com.brights.guestbook2.model.User;
import com.brights.guestbook2.repository.PostRepository;
import com.brights.guestbook2.repository.UserRepository;
import com.brights.guestbook2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private PostService postService;

    @Autowired
    public PostController() {

    }

    @GetMapping("/post/add")
    public String addPost(Model model){
        model.addAttribute("post", new Post());
        return "post/new";
    }

    @PostMapping("/post/checkPost{username}")
    public String checkPost(@PathVariable(value = "username") String username, @Valid Post post, BindingResult bindingResult){
        User user = userRepository.findByUsername(username);
        if (bindingResult.hasErrors()) {
            return "post/new";
        }
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/";
    }
    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable(value = "id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/post/addComment/")
    public String showCommentForm(Model model){
        model.addAttribute("comment", new Comment());
        return "post/comment";
    }

    @PostMapping("/post/addComment/{username}")
    public String addComment(@ModelAttribute Post post, @Valid Comment comment, BindingResult bindingResult, Model model, @PathVariable String username){
        if (bindingResult.hasErrors()) {
            return "index";
        }
        comment.setUser(userRepository.findByUsername(username));
        post.addComment(comment);
        return "redirect:/index";
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

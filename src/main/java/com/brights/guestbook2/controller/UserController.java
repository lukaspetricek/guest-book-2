package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.PostService;
import com.brights.guestbook2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService; //invoking interface only

    private PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("listOfAllUser", userService.getAllUsers());

        return "users/index";
    }

    @GetMapping("users/registration")
    public String showUserRegistration(Model model) {
        model.addAttribute("user", new User());

        return "users/registration";
    }

    @PostMapping("users/registration")
    public String saveUserRegistration(@Valid @ModelAttribute User user,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "users/registration";
        }

        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping("/users/error")
    public String showErrorPage(Model model) {
        //model.addAttribute("user", new User());

        return "users/error";
    }

    @GetMapping("/users/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("listOfAllUser", userService.getAllUsers());

        return "users/admin";
    }

    @GetMapping("users/admin/showFormForDelete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {

        userService.deleteUserById(id);
        return "redirect:/users/admin";
    }
}

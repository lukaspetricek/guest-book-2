package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.PostService;
import com.brights.guestbook2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Controller
public class UserController {


    private final UserService userService; //invoking interface only

    private final PostService postService;

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
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "users/registration";
    }

    @PostMapping("users/registration")
    public String saveUserRegistration(@Valid @ModelAttribute User user,
            BindingResult bindingResult, Model model) {
        model.addAttribute("user", user);

        if (bindingResult.hasErrors()) {
            return "users/registration";
        }
        userService.saveUser(user);

        return "redirect:/";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);

        return "users/register_success";
    }

    @GetMapping("/users/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listOfAllUser", userService.getAllUsers());

        return "users/admin";
    }

    @GetMapping("users/admin/showFormForEdit/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        userService.saveUser(user);

        return "users/edit";
    }

    @GetMapping("users/admin/showFormForDelete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {

        userService.deleteUserById(id);
        return "redirect:/users/admin";
    }

    @SuppressWarnings("unused")
    @GetMapping("/users/error")
    public String showErrorPage(Model model) {
        //model.addAttribute("user", new User());

        return "users/error";
    }
}

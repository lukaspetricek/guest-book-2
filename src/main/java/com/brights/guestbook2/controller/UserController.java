package com.brights.guestbook2.controller;

import com.brights.guestbook2.model.User;
import com.brights.guestbook2.service.PostServiceImpl;
import com.brights.guestbook2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@SuppressWarnings({"FieldCanBeLocal", "unused", "FieldMayBeFinal"})
@Controller
public class UserController {


    private UserServiceImpl userService; //invoking interface only

    private PostServiceImpl postService;

    @Autowired
    public UserController(UserServiceImpl userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public String showUsers(Principal userPrincipal, Model model) {
        User user = userService.getUserByUsername(userPrincipal.getName());
        model.addAttribute("listOfAllUser", userService.getAllUsers());
        if (user.isAdmin()){
            return "redirect:/users/admin";
        }else {
            return "redirect:/users/index";
        }
    }

    @GetMapping("users/registration")
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "users/registration";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if (userService.getAllUsers().size()==0){
            System.out.println("Admin set");
            user.setAdmin(true);
        }

        userService.saveUser(user);

        return "users/register_success";
    }

    @GetMapping("/users/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("listOfAllUser", userService.getAllUsers());

        return "users/admin";
    }@GetMapping("/users/index")
    public String showUserPage(Model model) {
        model.addAttribute("listOfAllUser", userService.getAllUsers());

        return "users/index";
    }

    @GetMapping("users/admin/showFormForEdit/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "users/edit";
    }
    @PostMapping("/users/admin/editUser")
    public String saveEditedUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.saveUser(user);


        return "redirect:/users/admin";
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

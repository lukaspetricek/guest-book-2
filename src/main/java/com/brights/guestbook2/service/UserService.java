package com.brights.guestbook2.service;

import com.brights.guestbook2.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(long id);
    User getUserByUsername(String username);
    void deleteUserById(long id);
}

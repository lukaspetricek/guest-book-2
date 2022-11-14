package com.brights.guestbook2.service;

import com.brights.guestbook2.model.User;

import java.util.List;

@SuppressWarnings("unused")
public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(long id);
    void deleteUserById(long id);
}

package com.brights.guestbook2.service;

import com.brights.guestbook2.model.User;
import com.brights.guestbook2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public User getUserById(long id) {
        Optional<User> optional = this.userRepository.findById(id);  //impl. could be null or empty
        User user = null;

        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User with id: " + id + " does not exist.");
        }
        return user;
    }

    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
}
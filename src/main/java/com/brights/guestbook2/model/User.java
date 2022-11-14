package com.brights.guestbook2.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30, message = "Username/nickname size should be between 2 and 30 characters.")
    private String username;

    @Column
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 50, message = "Password size should be between 8 and 30 characters.")
    private String password;

    @Column
    private boolean admin;

    @OneToMany (mappedBy = "user")
    private List<Post> post;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}

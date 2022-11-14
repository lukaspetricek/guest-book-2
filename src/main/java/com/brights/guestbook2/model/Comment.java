package com.brights.guestbook2.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date",nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 250, message = "Comment should be between 2 and 250 letters")
    private String content;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;
    public Comment(Date createdAt) {
        this.createdAt = LocalDateTime.now();
    }

    public Comment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

package com.brights.guestbook2.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name ="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @NotEmpty(message = "Title should not be empty.")
    @Column(name = "title",nullable = false)
    private String title;

    @NotEmpty(message = "Message should not be empty.")
    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "date",nullable = false)
    private LocalDateTime postedWhen;

    public Post() {
        postedWhen = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostedWhen() {
        return postedWhen;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostedWhen(LocalDateTime postedWhen) {
        this.postedWhen = postedWhen;
    }
}

package com.brights.guestbook2.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name ="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @NotEmpty(message = "Title should not be empty.")
    @NotNull
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Message should not be empty.")
    @NotNull
    @Column(name = "post")
    private String post;

    private Instant postedWhen;

    public Message() {
    }

    public Message(long id, User user, String title, String post, Instant postedWhen) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.post = post;
        this.postedWhen = postedWhen;
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

    public String getPost() {
        return post;
    }

    public Instant getPostedWhen() {
        return postedWhen;
    }
}

package com.brights.guestbook2.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

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
    @NotNull
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Message should not be empty.")
    @NotNull
    @Column(name = "content")
    private String content;

    private Instant postedWhen;

    public Post() {
    }

    public Post(long id, User user, String title, String content, Instant postedWhen) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public Instant getPostedWhen() {
        return postedWhen;
    }
}

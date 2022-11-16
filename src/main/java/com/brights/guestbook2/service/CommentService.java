package com.brights.guestbook2.service;

import com.brights.guestbook2.model.Comment;

import java.util.List;

@SuppressWarnings("unused")
public interface CommentService {
    List<Comment> getAllComments();

    void saveComment(Comment comment);

    Comment getCommentById(long id);

    void deleteCommentById(long id);

}

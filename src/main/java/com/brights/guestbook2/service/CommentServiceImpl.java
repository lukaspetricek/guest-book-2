package com.brights.guestbook2.service;

import com.brights.guestbook2.model.Comment;
import com.brights.guestbook2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("FieldMayBeFinal")
@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(@Lazy CommentRepository commentRepository) {
        super();
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void saveComment(Comment comment) {
        this.commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(long id) {
        Optional<Comment> optionalComment = this.commentRepository.findById(id);
        Comment comment;

        if(optionalComment.isPresent()){
            comment = optionalComment.get();
        }else {
            throw new RuntimeException("Comment with id " + id + " was not found!");
        }
        return comment;

    }

    @Override
    public void deleteCommentById(long id) {
        boolean exists = commentRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("Comment with id " + id + " was not found.");
        }
        commentRepository.deleteById(id);

    }
}

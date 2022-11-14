package com.brights.guestbook2.service;

import com.brights.guestbook2.model.Post;
import com.brights.guestbook2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public Post getPostById(long id) {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        Post post = null;

        if(optionalPost.isPresent()){
            post = optionalPost.get();
        }else {
            throw new RuntimeException("Post with id " + id + " was not found!");
        }
        return post;
    }

    @Override
    public void deletePostById(long id) {
        boolean exists = postRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("Post with id " + id + " was not found.");
        }
        postRepository.deleteById(id);
    }
}

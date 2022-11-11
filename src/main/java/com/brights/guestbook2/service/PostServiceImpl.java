package com.brights.guestbook2.service;

import com.brights.guestbook2.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public void savePost(Post post) {

    }

    @Override
    public Post getPostById(long id) {
        return null;
    }

    @Override
    public void deletePostById(long id) {

    }
}

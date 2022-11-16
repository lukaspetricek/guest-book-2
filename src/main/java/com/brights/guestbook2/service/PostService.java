package com.brights.guestbook2.service;

import com.brights.guestbook2.model.Post;

import java.util.List;


public interface PostService {
    List<Post> getAllPosts();

    void savePost(Post post);

    Post getPostById(long id);

    void deletePostById(long id);
}

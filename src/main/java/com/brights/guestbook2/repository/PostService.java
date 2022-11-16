package com.brights.guestbook2.repository;

import com.brights.guestbook2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostService extends JpaRepository<Post, Long> {
}

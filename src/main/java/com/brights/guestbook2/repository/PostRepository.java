package com.brights.guestbook2.repository;

import com.brights.guestbook2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

package com.brights.guestbook2.repository;

import com.brights.guestbook2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface UserRepository extends JpaRepository<User, Long> {
}

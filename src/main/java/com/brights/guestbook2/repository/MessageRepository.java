package com.brights.guestbook2.repository;

import com.brights.guestbook2.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface MessageRepository extends JpaRepository<Message, Long> {
}

package com.brights.guestbook2.service;

import com.brights.guestbook2.model.Message;

import java.util.List;

@SuppressWarnings("unused")
public interface MessageService {
    List<Message> getAllMessages();

    void saveMessage(Message message);

    Message getMessageById(long id);

    void deleteMessageById(long id);
}

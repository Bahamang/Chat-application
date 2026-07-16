package com.example.chatty.service;
import com.example.chatty.models.dto.ChatRoom;
import com.example.chatty.models.dto.Media;
import com.example.chatty.models.dto.Message;
import com.example.chatty.models.entity.MessageEntity;
import com.example.chatty.repository.MediaRepository;
import com.example.chatty.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    ProfileService profileService;

    public MessageEntity getById(Long messageId) {
        return messageRepository.getById(messageId);
    }

    public List<Message> getByChatId(Long chatId) {
        List<MessageEntity> messageEntities = messageRepository.getByChatId(chatId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Message> messages = mapper.convertValue(messageEntities, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Message.class));
        for(Message message:messages){
            if(message.getMediaId()!=null)
                message.setMedia(mapper.convertValue(mediaRepository.getById(message.getMediaId()), Media.class));
            if(message.getSenderId()!=null)
                message.setSenderProfile(profileService.getById(message.getSenderId()));
        }
        return messages;
    }

    public void update(MessageEntity messageEntity) {
        messageRepository.update(messageEntity);
    }

    public void create(MessageEntity messageEntity) {
        messageRepository.create(messageEntity);
    }

    public List<Message> getAll() {
        List<MessageEntity> messageEntities = messageRepository.getAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Message> messages = mapper.convertValue(messageEntities, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Message.class));
        for(Message message:messages){
            if(message.getMediaId()!=null)
                message.setMedia(mapper.convertValue(mediaRepository.getById(message.getMediaId()), Media.class));
            if(message.getSenderId()!=null)
                message.setSenderProfile(profileService.getById(message.getSenderId()));
        }
        return messages;
    }
}
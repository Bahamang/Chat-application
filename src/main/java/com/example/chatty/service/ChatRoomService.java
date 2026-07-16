package com.example.chatty.service;
import com.example.chatty.models.dto.ChatRoom;
import com.example.chatty.models.dto.Media;
import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.entity.ChatRoomEntity;
import com.example.chatty.models.entity.MessageEntity;
import com.example.chatty.models.entity.ProfileEntity;
import com.example.chatty.models.entity.UserEntity;
import com.example.chatty.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;

    public ChatRoom getById(Long chatRoomId, Long userId) {
        ChatRoomEntity chatRoomEntity = chatRoomRepository.getById(chatRoomId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ChatRoom chatRoom = mapper.convertValue(chatRoomEntity, ChatRoom.class);
        Long tmpId = !Objects.equals(chatRoom.getFirstUsersId(), userId) ? chatRoom.getFirstUsersId() : chatRoom.getSecondUsersId();
        if(tmpId!=null)
            chatRoom.setProfile(profileService.getById(tmpId));
        MessageEntity messageEntity = messageRepository.getLastMessage(chatRoomId);
        chatRoom.setLastMessage(messageEntity.getText());
        chatRoom.setLastMessageTime(messageEntity.getCreated());
        chatRoom.setSenderId(messageEntity.getSenderId());
        return chatRoom;
    }

    public void update(ChatRoomEntity chatRoomEntity) {
        chatRoomRepository.update(chatRoomEntity);
    }

    public void create(ChatRoomEntity chatRoomEntity) {
        chatRoomRepository.create(chatRoomEntity);
    }

    public List<Profile> startChat() {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.getUserByName(authUser.getUsername());
        List<ChatRoomEntity> chatRoomEntities = chatRoomRepository.getChatRoomsByFirstAndSecondUserIds(user.getId());
        Set<Long> myOpenChats = new HashSet<>();
        for(ChatRoomEntity chatRoomEntity1 : chatRoomEntities){
            myOpenChats.add(chatRoomEntity1.getFirstUsersId());
            myOpenChats.add(chatRoomEntity1.getSecondUsersId());
        }
        List<ProfileEntity> profileEntities = profileRepository.getByNotInListId(new ArrayList<>(myOpenChats));
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Profile> profiles = mapper.convertValue(profileEntities, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Profile.class));
        return profiles;
    }

    public ChatRoom createNewChat(Long secondUser) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.getUserByName(authUser.getUsername());
        ChatRoomEntity tmpChatRoomEntity = ChatRoomEntity.builder().firstUsersId(user.getId()).secondUsersId(secondUser).build();
        Long currentChat = chatRoomRepository.create(tmpChatRoomEntity);

        ChatRoomEntity chatRoomEntity = chatRoomRepository.getById(currentChat);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ChatRoom chatRoom = mapper.convertValue(chatRoomEntity, ChatRoom.class);
        Long tmpId = !Objects.equals(chatRoom.getFirstUsersId(), user.getId()) ? chatRoom.getFirstUsersId() : chatRoom.getSecondUsersId();

        if(tmpId!=null)
            chatRoom.setProfile(profileService.getById(tmpId));

        return chatRoom;
    }


    public List<ChatRoomEntity> getAll() {
        return chatRoomRepository.getAll();
    }


    public List<ChatRoom> getChatRoomsByFirstAndSecondUserIds(Long firstUserId) {
        List<ChatRoomEntity> chatRoomEntity = chatRoomRepository.getChatRoomsByFirstAndSecondUserIds(firstUserId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<ChatRoom> chatRooms = mapper.convertValue(chatRoomEntity, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, ChatRoom.class));
        for (ChatRoom chatRoom : chatRooms) {
            Long tmpId = !Objects.equals(chatRoom.getFirstUsersId(), firstUserId) ? chatRoom.getFirstUsersId() : chatRoom.getSecondUsersId();
            if(tmpId!=null)
                chatRoom.setProfile(profileService.getById(tmpId));
            MessageEntity messageEntity = messageRepository.getLastMessage(chatRoom.getId());
            chatRoom.setLastMessage(messageEntity.getText());
            chatRoom.setLastMessageTime(messageEntity.getCreated());
            chatRoom.setSenderId(messageEntity.getSenderId());
        }
        if(!chatRooms.isEmpty() && chatRooms.get(0).getLastMessageTime()!=null)
        Collections.sort(chatRooms, (chatRoom1, chatRoom2) -> chatRoom2.getLastMessageTime().compareTo(chatRoom1.getLastMessageTime()));
        return chatRooms;
    }

}
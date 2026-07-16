package com.example.chatty.service;

import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.entity.ProfileEntity;
import com.example.chatty.models.entity.UserEntity;
import com.example.chatty.repository.ProfileRepository;
import com.example.chatty.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileService profileRepository;


    public List<UserEntity> getListOfUsers(){
        return userRepository.getAllUsers();
    }


    public UserEntity getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    public UserEntity getUserByName(String username) {
        return userRepository.getUserByName(username);
    }

    public void createUser(UserEntity user) {
        userRepository.createUser(user);
    }

    public void disableUser(UserEntity user) {
        userRepository.disableUser(user);
    }

    public Profile whoAmI() {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.getUserByName(authUser.getUsername());
        Profile tmpProfile = profileRepository.getById(user.getId());
        return tmpProfile;
    }


    public void changePassword(String password) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.getUserByName(authUser.getUsername());
        user.setPassword(password);
        userRepository.changePassword(user);
    }


    public boolean checkPassword(String password) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.getUserByName(authUser.getUsername());
        return user.getPassword().equals(password);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserByName(username);

        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("User"));

        if(user==null){
            throw new UsernameNotFoundException("User not exists by Username");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                new StringBuilder( user.getPassword()).toString(),
                authorities
        );
    }

}

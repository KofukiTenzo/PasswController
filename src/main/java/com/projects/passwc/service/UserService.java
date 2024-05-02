package com.projects.passwc.service;

import com.projects.passwc.DTO.UserRegisterDTO;
import com.projects.passwc.Entitys.User;
import com.projects.passwc.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserRegisterDTO input) {
        User user = new User(
                input.getUsername(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword())
        );

        return userRepository.save(user);
    }

    public User getAuthentication(String username) {
        return userRepository.findUserByUsername(username).get();
    }

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);

    public boolean userExistByEmail(String email) {
        return findUserByEmail(email).isPresent();
    }

    public boolean userExistByUsername(String username) {
        return findUserByUsername(username).isPresent();
    }

}

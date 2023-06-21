package com.projects.passwc.forms;

import com.projects.passwc.DAO.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterForm {

    @NotNull
    @Size(min = 5, max = 16, message = "Username must be between {min} and {max} characters long.")
    private String username;

    @NotNull
    @Email(message = "Email is mandatory.")
    private String email;

    @NotNull
    @Size(min = 8, max = 16, message = "Password must be between {min} and {max} characters long.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new User(username, email, passwordEncoder.encode(password));
    }}

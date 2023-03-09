package com.projects.passwc.web;

import com.projects.passwc.entity.User;
import org.hibernate.validator.constraints.Email;

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
    @Size(min = 8, max = 15, message = "Password must be between {min} and {max} characters long.")
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
        return new User(username, email, password);
    }
}

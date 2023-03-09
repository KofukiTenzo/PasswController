package com.projects.passwc.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    private Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "Username must be between {min} and {max} characters long.")
    private String username;

    @NotNull
    @Email(message = "Email is mandatory.")
    private String email;

    @NotNull
    @Size(min = 8, max = 15, message = "Password must be between {min} and {max} characters long.")
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this(null, username, email, password);
    }

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "username", "email", "password");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "username", "email", "password");
    }
}

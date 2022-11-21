package com.projects.passwc;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswcUser {
    private Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "Username must be between {min} and {max} characters long.")
    private String username;

    @NotNull
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull
    @Size(min = 8, max = 15, message = "Password must be between {min} and {max} characters long.")
    private String passwd;

    public PasswcUser() {
    }

    public PasswcUser(String username, String email, String passwd) {
        this(null, username, email, null);
    }

    public PasswcUser(Long id, String username, String email, String passwd) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwd = passwd;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

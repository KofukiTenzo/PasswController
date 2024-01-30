package com.projects.passwc.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterDTO {

    @Size(min = 4, max = 20/*, message = "Username must be between {min} and {max} characters long."*/)
    private String username;

    @NotEmpty
    @Email
    private String email;

    @Size(min = 6, max = 16/*, message = "Password must be between {min} and {max} characters long."*/)
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
}

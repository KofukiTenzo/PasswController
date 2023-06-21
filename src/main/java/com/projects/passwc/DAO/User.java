package com.projects.passwc.DAO;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotNull
//    @Size(min = 5, max = 16, message = "Username must be between {min} and {max} characters long.")
    @Column(name = "username")
    private String username;

    //    @NotNull
//    @Email(message = "Email is mandatory.")
    @Column(name="email")
    private String email;

    //    @NotNull
//    @Size(min = 8, max = 16, message = "Password must be between {min} and {max} characters long.")
    @Column(name="password")
    private String password;

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

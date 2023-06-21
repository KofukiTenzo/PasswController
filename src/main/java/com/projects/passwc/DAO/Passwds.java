package com.projects.passwc.DAO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Passwds {

    public Passwds() {}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column
    private String resourceName;
    @Column
    private String passwd;
    @Column
    private Date creation_date;

    public Passwds(String resourceName, String passwd) {
        this(null, null, resourceName, passwd, new Date());
    }

    public Passwds(Long id, User user, String resourceName, String passwd, Date creation_date) {
        this.id = id;
        this.user = user;
        this.resourceName = resourceName;
        this.passwd = passwd;
        this.creation_date = creation_date;
    }

    public Long getId() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getPasswd() {
        return passwd;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public User getUser() {
        return user;
    }
}

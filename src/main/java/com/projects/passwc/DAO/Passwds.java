package com.projects.passwc.DAO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passwds")
public class Passwds {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

//    @OneToMany
    @JoinColumn(name = "user")
    private String user;

    @Column(name = "resourceName")
    private String resourceName;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "creation_date")
    private Date creation_date;



    public Passwds() {}

    public Passwds(String user, String resourceName, String passwd){
        this.user = user;
        this.resourceName = resourceName;
        this.passwd = passwd;
        this.creation_date = new Date();
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

    public String getUser() {
        return user;
    }
}



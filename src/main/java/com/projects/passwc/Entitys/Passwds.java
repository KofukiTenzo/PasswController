package com.projects.passwc.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "passwds")
public class Passwds {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    @Column(name = "PASSWD")
    private String passwd;

    @Column(name = "CREATION_DATE")
    private String creation_date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Passwds() {}

    public Passwds(String resourceName, String passwd, String creation_date){
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

    public String getCreation_date() {
        return creation_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void assignUser(User user) {
        this.user = user;
    }
}


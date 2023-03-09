package com.projects.passwc.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;

public class Passwds {
    private final Long id;
    private String resource_n;
    private final String passwd;
    private final Date creation_date;

    public Passwds(String resource_n, String passwd) {
        this(null, resource_n, passwd, new Date());
    }

    public Passwds(Long id, String resource_n, String passwd, Date creation_date) {
        this.id = id;
        this.resource_n = resource_n;
        this.passwd = passwd;
        this.creation_date = creation_date;
    }

    public Long getId() {
        return id;
    }

    public String getResource_n() {
        return resource_n;
    }

    public String getPasswd() {
        return passwd;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "id", "date");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }
}

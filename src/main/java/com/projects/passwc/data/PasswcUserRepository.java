package com.projects.passwc.data;

import com.projects.passwc.PasswcUser;

public interface PasswcUserRepository {

    PasswcUser save(PasswcUser passwcUser);

    PasswcUser findByUsername(String username);
}

package com.projects.passwc.data;

import com.projects.passwc.entity.User;

public interface UserRegisterRepository {

    User save(User user);

    User findByUsername(String username);
}

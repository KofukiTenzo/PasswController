package com.projects.passwc.data;

import com.projects.passwc.DAO.User;

import java.util.List;

public interface UserRegisterRepository {

    long count();

    User save(User user);

    User findOne(long id);

    User findByUsername(String username);

    List<User> findAll();
}

package com.projects.passwc.data;

import com.projects.passwc.DAO.Passwds;

import java.util.List;

public interface PasswdsRepository {

    long count();

    List<Passwds> findRecent(String username);

    List<Passwds> findRecent(String username, int count);

    Passwds findOne(long id);

    Passwds save(Passwds passwds);

    List<Passwds> findByPasswdId(long passwdId);

    void delete(long id);
}

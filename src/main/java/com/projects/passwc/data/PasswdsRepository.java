package com.projects.passwc.data;

import com.projects.passwc.DAO.Passwds;

import java.util.List;

public interface PasswdsRepository {

    List<Passwds> findAllUserPasswds(String username);

    Passwds findOne(long id);

    List<Passwds> findByName(String username, String name);

    Passwds save(Passwds passwds);

    void delete(long id);

    PasswdsResponse findRecent(String name, int pageNo);
}

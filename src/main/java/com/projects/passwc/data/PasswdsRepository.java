package com.projects.passwc.data;

import com.projects.passwc.DAO.Passwds;

import java.util.List;

public interface PasswdsRepository {

    long count();

    List<Passwds> findRecent();

    List<Passwds> findRecent(int count);

    Passwds findOne(long id);

    Passwds save(Passwds passwds);

    List<Passwds> findByPasswdId(long passwdId);

    void delete(long id);
}

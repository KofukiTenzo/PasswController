package com.projects.passwc.data;

import com.projects.passwc.entity.Passwds;

import java.util.List;

public interface PasswdsRepository {
    List<Passwds> findRecentPasswds();

    List<Passwds> findPasswds(long max, int count);

    Passwds findOne(long id);

    void save(Passwds passwds);
}

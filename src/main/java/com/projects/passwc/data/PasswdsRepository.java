package com.projects.passwc.data;

import com.projects.passwc.Entitys.Passwds;
import com.projects.passwc.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswdsRepository extends JpaRepository<Passwds, Long> {

    List<Passwds> findAllByUser(User user);
}

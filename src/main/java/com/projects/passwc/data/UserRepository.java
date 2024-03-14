package com.projects.passwc.data;

import com.projects.passwc.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    User save(User user);

    void deleteById(Long id);
}

package com.projects.passwc.data;

import com.projects.passwc.DAO.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaUserRegisterRepository implements UserRegisterRepository{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findOne(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return (User) entityManager.createQuery("select u from User u where u.username=?1").setParameter(1, username).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return (List<User>) entityManager.createQuery("select u from User u").getResultList();
    }
}

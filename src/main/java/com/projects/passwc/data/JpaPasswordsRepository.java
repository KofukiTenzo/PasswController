package com.projects.passwc.data;

import com.projects.passwc.DAO.Passwds;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaPasswordsRepository implements PasswdsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public List<Passwds> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Passwds> findRecent(int count) {
        return (List<Passwds>) entityManager.createQuery("select p from Passwds p order by p.creation_date desc")
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public Passwds findOne(long id) {
        return entityManager.find(Passwds.class, id);
    }

    @Override
    public Passwds save(Passwds passwds) {
        entityManager.persist(passwds);
        return passwds;
    }

    @Override
    public List<Passwds> findByPasswdId(long passwdId) {
        return (List<Passwds>) entityManager.createQuery("select p from Passwds p, User u where p.user = u and u.id=?1 order by p.creation_date desc")
                .setParameter(1, passwdId)
                .getResultList();
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findOne(id));
    }

    public List<Passwds> findAll() {
        return (List<Passwds>) entityManager.createQuery("select p from Passwds p").getResultList();
    }
}

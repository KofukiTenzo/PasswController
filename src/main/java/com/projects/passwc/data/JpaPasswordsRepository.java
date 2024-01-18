package com.projects.passwc.data;

import com.projects.passwc.DAO.Passwds;
import org.springframework.beans.support.PagedListHolder;
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
    public List<Passwds> findAllUserPasswds(String username) {
        return (List<Passwds>) entityManager
                .createQuery("select p from Passwds p where p.user=?1 order by p.creation_date desc")
                .setParameter(1, username)
                .getResultList();
    }

    @Override
    public PasswdsResponse findRecent(String username, int pageNumber) {
        PagedListHolder<Passwds> pagedListHolder = new PagedListHolder(findAllUserPasswds(username));
        pagedListHolder.setPageSize(7);
        pagedListHolder.setPage(pageNumber);

        PasswdsResponse passwdsResponse = new PasswdsResponse();
        passwdsResponse.setPasswdsList(pagedListHolder.getPageList());
        passwdsResponse.setPageNumber(pagedListHolder.getPage());
        passwdsResponse.setPageSize(pagedListHolder.getPageSize());
        passwdsResponse.setTotalElements(pagedListHolder.getNrOfElements());
        passwdsResponse.setTotalPages(pagedListHolder.getPageCount());
        passwdsResponse.setLast(pagedListHolder.isLastPage());

        return passwdsResponse;
    }

    @Override
    public Passwds findOne(long id) {
        return entityManager
                .find(Passwds.class, id);
    }

    @Override
    public List<Passwds> findByName(String username, String name) {
        return (List<Passwds>) entityManager
                .createQuery("select p from Passwds p where p.user=?1 and p.resourceName=?2 order by p.creation_date desc")
                .setParameter(1, username)
                .setParameter(2, name)
                .getResultList();
    }

    @Override
    public Passwds save(Passwds passwds) {
        entityManager
                .persist(passwds);
        return passwds;
    }

    @Override
    public void delete(long id) {
        entityManager
                .remove(findOne(id));
    }

//    @Override
//    public long count() {
//        return findAll().size();
//    }

    public List<Passwds> findAll() {
        return (List<Passwds>) entityManager
                .createQuery("select p from Passwds p")
                .getResultList();
    }
}

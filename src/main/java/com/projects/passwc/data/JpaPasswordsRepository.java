package com.projects.passwc.data;

import com.projects.passwc.DAO.Passwds;
import com.projects.passwc.response.PasswdsResponse;
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
    public PasswdsResponse showPasswds(int pageNumber, List<Passwds> list) {
        PagedListHolder<Passwds> pagedListHolder = new PagedListHolder(list);
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

//    @Override
//    public List<Passwds> searchPasswds(String username, String query) {
//        return (List<Passwds>) entityManager
//                .createQuery("select p from Passwds p WHERE p.resourceName like concat('%', :query, '%') " +
//                        "or p.passwd like concat('%', :query, '%') ");
//    }

    @Override
    public Passwds findOne(long id) {
        return entityManager
                .find(Passwds.class, id);
    }

    @Override
    public List<Passwds> findByName(String username, String name) {
        return (List<Passwds>) entityManager
                .createQuery("select p from Passwds p where p.user=:username and p.resourceName=:name order by p.creation_date desc")
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

    public List<Passwds> findAll() {
        return (List<Passwds>) entityManager
                .createQuery("select p from Passwds p")
                .getResultList();
    }
}

package com.projects.passwc.data;

//@Repository
//@Transactional
//public class JpaUserRepository implements UserRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Override
//    public long count() {
//        return findAll().size();
//    }
//
//    @Override
//    public User save(User user) {
//        entityManager.persist(user);
//        return user;
//    }
//
//    @Override
//    public User findOne(long id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public User findByUsername(String username) {
//        return (User) entityManager.createQuery("select u from User u where u.username=?1").setParameter(1, username).getSingleResult();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return (List<User>) entityManager.createQuery("select u from User u").getResultList();
//    }
//}





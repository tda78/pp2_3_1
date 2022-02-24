package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<User> getUsers() {
        EntityManager em = emf.createEntityManager();
        List<User>users = em.createQuery("from User").getResultList();
        return users;

    }

    @Override
    public void saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr =em.getTransaction();
        tr.begin();
        user.setId(0L);
        em.persist(user);
        tr.commit();

    }

    @Override
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr =em.getTransaction();
        tr.begin();
        em.merge(user);
        tr.commit();

    }

    @Override
    public void deleteUser(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr =em.getTransaction();
        tr.begin();
        em.remove(em.merge(findUser(id)));
        tr.commit();

    }

    @Override
    public User findUser(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }
}

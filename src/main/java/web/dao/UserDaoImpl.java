package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getUsers() {
        List<User>users = em.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public void saveUser(User user) {
        user.setId(0L);
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        em.remove(em.merge(findUser(id)));
    }

    @Override
    public User findUser(long id) {
        return em.find(User.class, id);
    }
}

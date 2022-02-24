package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;

    public UserServiceImpl() {}

    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Override
    public User findUserByID(int id) {
        return dao.findUser(id);
    }

    @Override
    public void saveUser(User user) {
        if(user.getId()==0){
            dao.saveUser(user);
        }else {
            dao.updateUser(user);
        }
    }

    @Override
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }
}

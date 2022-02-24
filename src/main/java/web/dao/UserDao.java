package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(long id);
    User findUser(long id);
}


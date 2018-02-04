package TaskTracker.dao.interfaces;

import TaskTracker.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getUserById(int id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    void saveUser(User user);
    void delete(User user);
    void update(User user);
}

package TaskTracker.service.interfaces;

import TaskTracker.model.Project;
import TaskTracker.model.User;

import java.util.List;

public interface UserService {
    void addOwnProject(Project project);
    List<User> getAll();
    User getUserById(int id);
    String register(User user);
    String delete(User user);
    String update(User user);
}

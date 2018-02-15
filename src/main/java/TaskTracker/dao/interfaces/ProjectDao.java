package TaskTracker.dao.interfaces;

import TaskTracker.model.Project;
import TaskTracker.model.Task;
import TaskTracker.model.User;

import java.util.List;

public interface ProjectDao {
    void addTask(int id, Task task);
    void deleteTask(int id, Task task);
    void addDeveloper(int projectId, int developerId);
    void deleteDeveloper(int id, User user);
    Project getProjectById(int id);
    Project getProjectByTitle(String title);
    List<Project> getAllProjects();
    String save(Project project);
    void update(Project project);
    void delete(Integer id);


}

package TaskTracker.service.interfaces;

import TaskTracker.model.Project;
import TaskTracker.model.Task;
import TaskTracker.model.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface ProjectService {
    String addTask(int id, Task task);
    String deleteTask(int id, Task task);
    String addDeveloper(int projectId, int developerId);
    String deleteDeveloper(int id, User user);
    Project getProjectById(int id);
    Project getProjectByTitle(String title);
    List<Project> getAllProjects();
    String save(Project project);
    String update(Project project);
    String delete(Integer id);
}

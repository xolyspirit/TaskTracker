package TaskTracker.service;

import TaskTracker.dao.interfaces.ProjectDao;
import TaskTracker.dao.interfaces.UserDao;
import TaskTracker.model.Project;
import TaskTracker.model.Task;
import TaskTracker.model.User;
import TaskTracker.service.interfaces.ProjectService;
import TaskTracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectDao projectDao;

    @Autowired
    UserDao userDao;

    public String addTask(int id, Task task) {
        projectDao.addTask(id,task);
        if (projectDao.getProjectById(id).getTasks().contains(task)){
            return "Task added";
        }
        else {
            return "Something went wrong...";
        }
    }

    public String deleteTask(int id, Task task) {
        projectDao.deleteTask(id,task);
        if (!projectDao.getProjectById(id).getTasks().contains(task)){
            return "Task deleted";
        }
        else {
            return "Something went wrong...";
        }
    }

    public String addDeveloper(int projectId, int developerId) {
        projectDao.addDeveloper(projectId,developerId);
        if (projectDao.getProjectById(projectId).getDevelopers().contains(userDao.getUserById(developerId))){
            return "Developer added";
        }
        else {
            return "Something went wrong...";
        }
    }

    public String deleteDeveloper(int id, User user) {
        projectDao.deleteDeveloper(id,user);
        if (!projectDao.getProjectById(id).getDevelopers().contains(user)){
            return "Developer deleted";
        }
        else {
            return "Something went wrong...";
        }
    }

    public Project getProjectById(int id) {
        return projectDao.getProjectById(id);
    }

    public Project getProjectByTitle(String title) {
        return projectDao.getProjectByTitle(title);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public String save(Project project) {
        User manager = userDao.getUserById(project.getManager().getId());
        project.setManager(manager);
        return projectDao.save(project);
    }

    public String update(Project project) {
        projectDao.update(project);
        return "All done";
    }

    public String delete(Integer id) {
        projectDao.delete(id);
        if (projectDao.getProjectById(id)==null){
            return "Project deleted";
        }
        else {
            return "Something went wrong...";
        }
    }
}

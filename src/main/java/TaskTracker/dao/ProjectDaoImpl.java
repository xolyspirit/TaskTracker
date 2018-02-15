package TaskTracker.dao;

import TaskTracker.dao.interfaces.ProjectDao;
import TaskTracker.dao.interfaces.UserDao;
import TaskTracker.model.Project;
import TaskTracker.model.Task;
import TaskTracker.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addTask(int id, Task task) {
        Project updateProject  = (Project)sessionFactory.getCurrentSession().merge(id);
        if (updateProject!=null){
            updateProject.getTasks().add(task);
        }
        else {
            Set<Task> tasks = new HashSet<>();
            tasks.add(task);
            updateProject.setTasks(tasks);
        }
        sessionFactory.getCurrentSession().flush();
    }

    public void deleteTask(int id, Task task) {
        Project updateProject  = (Project)sessionFactory.getCurrentSession().merge(id);
        updateProject.getTasks().remove(task);
        sessionFactory.getCurrentSession().flush();
    }

    public void addDeveloper(int projectId, int developerId) {
        Session session = sessionFactory.getCurrentSession();
        Project project = session.get(Project.class, projectId);
        User developer = session.get(User.class, developerId);
        developer.getProjects().add(project);
        session.merge(developer);
    }

    public void deleteDeveloper(int id, User user) {
        Project updateProject  = (Project)sessionFactory.getCurrentSession().merge(id);
        updateProject.getDevelopers().remove(user);
        sessionFactory.getCurrentSession().flush();
    }

    public Project getProjectById(int id) {
        return sessionFactory.getCurrentSession().get(Project.class, id);
    }

    public Project getProjectByTitle(String title) {
        Criteria userCriteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
        userCriteria.add(Restrictions.eq("title", title));
        return (Project)userCriteria.uniqueResult();
    }

    public List<Project> getAllProjects() {
        CriteriaQuery<Project> cq = sessionFactory.getCriteriaBuilder().createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        cq.select(root);
        Query<Project> query = sessionFactory.getCurrentSession().createQuery(cq);
        return query.getResultList();
    }

    public String save(Project project) {
        sessionFactory.getCurrentSession().save(project);
        if (sessionFactory.getCurrentSession().contains(project)){
            return "Project saved";
        }
        else {
            return "Something went wrong...";
        }
    }

    public void update(Project project) {
     Project updateProject  = (Project)sessionFactory.getCurrentSession().merge(project.getId());
     if (project.getBody()!=null) {
         updateProject.setBody(project.getBody());
     }
     if (project.getTitle()!=null){
         updateProject.setTitle(project.getTitle());
     }
     if (project.getTasks().size()!=0){
         updateProject.setTasks(project.getTasks());
     }
     if (project.getDevelopers().size()!=0){
            updateProject.setDevelopers(project.getDevelopers());
     }
     sessionFactory.getCurrentSession().flush();

    }

    public void delete(Integer id) {
        Project project = new Project();
        project.setId(id);
        sessionFactory.getCurrentSession().delete(project);
        sessionFactory.getCurrentSession().flush();
    }
}

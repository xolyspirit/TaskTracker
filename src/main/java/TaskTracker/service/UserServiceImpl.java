package TaskTracker.service;

import TaskTracker.dao.interfaces.UserDao;
import TaskTracker.model.Project;
import TaskTracker.model.User;
import TaskTracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void addOwnProject(Project project) {
       User user =  userDao.getUserById(project.getManager().getId());
       user.getOwnProjects().add(project);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public String register(User user) {
        String answer;
        if (userDao.getUserByEmail(user.getEmail())==null){
            if (userDao.getUserByUsername(user.getUsername())==null){
                userDao.saveUser(user);
                if(userDao.getUserByUsername(user.getUsername())!=null){
                    answer = " Registration complete successful";
                }
                else {
                    answer = "Something went wrong...";
                }

            }
            else {
                answer = "This username already used";
            }
        }
        else {
            answer = "This email already used";
        }
        return answer;
    }

    public String delete(User user) {
        if(user.getId()==0){
            user.setId(userDao.getUserByUsername(user.getUsername()).getId());
        }
        userDao.delete(user);
        if(userDao.getUserById(user.getId())!= null){
            return "Something went wrong...";
        }
        return "All done";
    }

    public String update(User user) {
        userDao.update(user);
        return "All done";
    }

}

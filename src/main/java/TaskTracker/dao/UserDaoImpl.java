package TaskTracker.dao;

import TaskTracker.dao.interfaces.UserDao;
import TaskTracker.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAll() {
        CriteriaQuery<User> cq =sessionFactory.getCriteriaBuilder().createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Query<User> query = sessionFactory.getCurrentSession().createQuery(cq);
        return query.getResultList();
    }

    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class,id);
    }

    public User getUserByEmail(String email) {
        User user;
        Criteria userCriteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        userCriteria.add(Restrictions.eq("email", email));
        user = (User)userCriteria.uniqueResult();
        return user;
    }

    public User getUserByUsername(String username) {
        Criteria userCriteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        userCriteria.add(Restrictions.eq("username", username));
        return (User)userCriteria.uniqueResult();
    }

    public void saveUser(User user){
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().flush();
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
        sessionFactory.getCurrentSession().flush();
    }

    public void update(User user) {
        User updateUser = (User)sessionFactory.getCurrentSession().merge(getUserByUsername(user.getUsername()));
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        if(user.getEnabled()==1){
            updateUser.setEnabled((byte)1);
        }
        if (user.getRole()!=null){
            updateUser.setRole(user.getRole());
        }
        sessionFactory.getCurrentSession().flush();
    }
}

package TaskTracker.dao.interfaces;

import TaskTracker.model.Task;

public interface TaskDao {
    Task getTask(int id);
}

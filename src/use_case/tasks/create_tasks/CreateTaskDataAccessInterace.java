package use_case.tasks.create_tasks;

import java.io.IOException;
import java.util.List;

public interface CreateTaskDataAccessInterace {
    void saveTask(CreateTaskInputData taskData) throws IOException;
    List<String> readAllTasks() throws IOException;
}

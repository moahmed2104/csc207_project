package data_access;

import use_case.CreateNewEvent.CreateEventDataAccessInterface;
import use_case.tasks.create_tasks.CreateTaskDataAccessInterace;
import use_case.tasks.create_tasks.CreateTaskInputData;

import java.io.IOException;
import java.util.List;

public class DummyDataAccess implements CreateEventDataAccessInterface, CreateTaskDataAccessInterace {
    @Override
    public void saveTask(CreateTaskInputData taskData) throws IOException {

    }

    @Override
    public List<String> readAllTasks() throws IOException {
        return null;
    }
}

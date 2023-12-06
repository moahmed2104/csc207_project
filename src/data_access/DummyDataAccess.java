package data_access;

import entity.HeadItem;
import entity.Item;
import use_case.CreateNewEvent.CreateEventDataAccessInterface;
import use_case.tasks.create_tasks.CreateTaskDataAccessInterace;
import use_case.tasks.create_tasks.CreateTaskInputData;

import java.io.IOException;
import java.util.List;

public class DummyDataAccess implements CreateEventDataAccessInterface {
    private HeadItem headItem;

    public DummyDataAccess(HeadItem headItem) {
        this.headItem = headItem;
    }

    @Override
    public void save(Item item) {
        System.out.println("successfully created Item: " + item.getDescription().toString());
    }

    @Override
    public HeadItem getHeadItem() {
        return this.headItem;
    }
    @Override
    public void saveTask(CreateTaskInputData taskData) throws IOException {

    }

    @Override
    public List<String> readAllTasks() throws IOException {
        return null;
    }
}

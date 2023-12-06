package data_access;

import entity.HeadItem;
import entity.Item;
import use_case.CreateNewEvent.CreateEventDataAccessInterface;
import use_case.DeleteEvent.DeleteEventDataAccessInterface;
import use_case.EditEvent.EditEventDataAccessInterface;
import use_case.tasks.create_tasks.CreateTaskDataAccessInterace;
import use_case.tasks.create_tasks.CreateTaskInputData;

import java.io.IOException;
import java.util.List;

public class DummyDataAccess implements CreateEventDataAccessInterface, EditEventDataAccessInterface, DeleteEventDataAccessInterface {
    public HeadItem headItem;
    public String lastSaid = "";

    public DummyDataAccess(HeadItem headItem) {
        this.headItem = headItem;
    }

    @Override
    public void save(Item item) {
        System.out.println("successfully created Item: " + item.getDescription().toString());
        lastSaid = "successfully created Item: " + item.getDescription().toString();
    }

    @Override
    public void update(Item item) {
        System.out.println("successfully Updated Item: " + item.getDescription().toString());
        lastSaid = "successfully Updated Item: " + item.getDescription().toString();
    }

    @Override
    public void delete(Item item) {
        System.out.println("successfully deleted Item: " + item.getDescription().toString());
        lastSaid = "successfully deleted Item: " + item.getDescription().toString();
    }

    @Override
    public HeadItem getHeadItem() {
        return this.headItem;
    }
}

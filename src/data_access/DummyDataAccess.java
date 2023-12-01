package data_access;

import entity.HeadItem;
import entity.Item;
import use_case.CreateNewEvent.CreateEventDataAccessInterface;

public class DummyDataAccess implements CreateEventDataAccessInterface {
    private HeadItem headItem;

    public DummyDataAccess(HeadItem headItem) {
        this.headItem = headItem;
    }

    @Override
    public void save(Item item) {
        System.out.println("successfully created Item with description" + item.getDescription().toString());
    }

    @Override
    public HeadItem getHeadItem() {
        return this.headItem;
    }

}

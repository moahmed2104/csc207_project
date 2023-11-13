package entity;

import java.util.List;

public class Tasks implements Item{
    Description description;
    List<Item> subItem;
    Status status;

    @Override
    public Description getDescription() {
        return this.description;
    }

    @Override
    public List<Item> getSubItem() {
        return this.subItem;
    }
    public void changeStatus() {

    }
}

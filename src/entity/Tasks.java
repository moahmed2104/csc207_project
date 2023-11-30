package entity;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public Item getParentItem() {
        return null;
    }

    @Override
    public void addSubItem(Item item) {

    }

    @Override
    public Boolean hasSubItem(String address) {
        return null;
    }

    @Override
    public Item findSubItem(String address) throws NoSuchElementException {
        return null;
    }

    @Override
    public Item navigate(String address) throws NoSuchElementException {
        return null;
    }

    public void changeStatus() {

    }
}

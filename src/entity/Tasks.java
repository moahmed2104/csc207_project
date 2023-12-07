package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Tasks implements Item {
    Description description;
    List<Item> subItem;

    Item parentItem;
    //Status status;

    String date;

    public Tasks(Description description, String date, Item parentItem) {
        this.description = description;
        this.subItem = new ArrayList<Item>();
        this.parentItem = parentItem;
        this.date = date;
    }

    @Override
    public Description getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
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
}

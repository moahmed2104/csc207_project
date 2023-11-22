package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
A class that represents a particular event in a calendar or task description


 */


public class Event implements Item{
    Description description;
    List<Item> subItem;

    Item parentItem;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Event(Description description, LocalDateTime startTime, LocalDateTime endTime, Item parentItem) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subItem = new ArrayList<Item>();
        this.parentItem = parentItem;
    }

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
    public String getAddress() {
        //todo finish implementing
        return null;
    }

    @Override
    public void addSubItem(Item item) {
        this.subItem.add(item);
    }

    @Override
    public Item navigate(String address) {
        /* returns the pointer to an item that matches the address given in string address,
        where address is of the form "root/child/child/item"
         */
        //todo implement
        return null;
    }

    public void changeTime(LocalDateTime newStartTime, LocalDateTime newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
    }

}

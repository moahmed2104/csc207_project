package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
A class that represents a particular event in a calendar or task description


 */


public class Event implements Item{
    Description description;
    String id;
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
    public void addSubItem(Item item) {
        this.subItem.add(item);
    }

    @Override
    public Boolean hasSubItem(String address) {
        for (Item item : subItem) {
            if (item.getDescription().getAddress() == address){
                return true;
            }
        }
        return false;
    }

    @Override
    public Item findSubItem(String address) throws Exception{
        for (Item item : subItem) {
            if (item.getDescription().getAddress() == address) {
                return item;
            }
        }
        throw new Exception("No such sub item");
    }

    @Override
    public Item navigate(String address) throws Exception{
        int indexSubAddress = address.indexOf("/");
        String subAddress = address.substring(indexSubAddress + 1);
        int indexChildAddress = subAddress.indexOf("/");
        String childDescriptor = subAddress.substring(indexChildAddress + 1);
        if (address.endsWith(this.description.getAddress())) {
            return this;
        } else if (hasSubItem(subAddress + "/" + childDescriptor)) {
            return findSubItem(subAddress + "/" + childDescriptor).navigate(subAddress);
        } else {
            throw new Exception("no such item");
        }
    }

    public void changeTime(LocalDateTime newStartTime, LocalDateTime newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public LocalDateTime getEndTime(){
        return this.endTime;
    }

}

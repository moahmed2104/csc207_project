package entity;

import java.time.LocalDateTime;
import java.util.*;

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
        return this.parentItem;
    }

    @Override
    public void addSubItem(Item item) {
        this.subItem.add(item);
    }

    @Override
    public Boolean hasSubItem(String address) {
        for (Item item : subItem) {
            if (Objects.equals(item.getDescription().getAddress(), address)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Item findSubItem(String address) throws NoSuchElementException{
        for (Item item : subItem) {
            if (item.getDescription().getAddress() == address) {
                return item;
            }
        }
        throw new NoSuchElementException("No such sub item");
    }

    @Override
    public Item navigate(String address) throws NoSuchElementException{
        Iterator<String> addressIterator = new AddressIterator(address).iterator();
        Item curr_item = this;
        addressIterator.next();
        while(addressIterator.hasNext()){
            curr_item = curr_item.findSubItem(addressIterator.next());
        }
        return curr_item;
    }

    public void changeTime(LocalDateTime newStartTime, LocalDateTime newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
    }

}

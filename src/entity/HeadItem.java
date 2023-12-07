package entity;

import java.util.*;

public class HeadItem implements Item{
    private List<Item> subItem;
    private Description description;

    //todo finish writing this class
    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public List<Item> getSubItem() {
        return subItem;
    }

    @Override
    public Item getParentItem() {
        return null;
    }

    @Override
    public void addSubItem(Item item) {
        subItem.add(item);
    }

    @Override
    public Boolean hasSubItem(String address) {
        for (Item item : subItem) {
            if (item.getDescription().getAddress() == address) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Item findSubItem(String address) throws NoSuchElementException{
        for (Item item : subItem) {
            if (Objects.equals(item.getDescription().getAddress(), address)) {
                return item;
            }
        }
        throw new NoSuchElementException();
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

    public HeadItem(DescriptionFactory descriptionFactory){
        this.subItem = new ArrayList<Item>();
        this.description = descriptionFactory.create("All", "All items in your planner", "all");
    }
}

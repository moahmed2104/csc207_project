package entity;

import java.util.ArrayList;
import java.util.List;

public class headItem implements Item{
    private List<Item> subItem;
    private DescriptionFactory descriptionFactory;
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

    public headItem(DescriptionFactory descriptionFactory){
        this.subItem = new ArrayList<Item>();
        this.descriptionFactory = descriptionFactory;
        this.descriptionFactory.create("All", "All items in your planner", "all");
    }
}

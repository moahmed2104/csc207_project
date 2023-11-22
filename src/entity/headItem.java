package entity;

import java.util.List;

public class headItem implements Item{
    private List<Item> subItem;

    //todo finish writing this class
    @Override
    public Description getDescription() {
        return new Description("All", "Every Item in your planner") ;
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
    public String getAddress() {
        return null;
    }

    @Override
    public void addSubItem(Item item) {

    }

    @Override
    public Item navigate(String address) {
        return null;
    }
}

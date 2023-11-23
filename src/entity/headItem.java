package entity;

import java.util.ArrayList;
import java.util.List;

public class headItem implements Item{
    private List<Item> subItem;
    private DescriptionFactory descriptionFactory;

    //todo finish writing this class
    @Override
    public Description getDescription() {
        return descriptionFactory.create("All", "All items in your planner", "all");
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
    public Boolean hasChild(String descriptor) {
        for (Item item : subItem) {
            if (item.getDescription().address.endsWith("/" + descriptor)) {
                return true;
            }
        }
        return false;
    }

    public headItem(DescriptionFactory descriptionFactory){
        subItem = new ArrayList<Item>();
        this.descriptionFactory = descriptionFactory;
    }
}

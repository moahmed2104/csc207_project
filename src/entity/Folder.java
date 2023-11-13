package entity;

import java.util.List;

public class Folder implements Item{
    Description description;
    List<Item> subItem;

    @Override
    public Description getDescription() {
        return this.description;
    }

    @Override
    public List<Item> getSubItem() {
        return this.subItem;
    }
}

package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Event implements Item{
    Description description;
    List<Item> subItem;

    LocalDateTime startTime;
    LocalDateTime endTime;

    @Override
    public Description getDescription() {
        return this.description;
    }

    @Override
    public List<Item> getSubItem() {
        return this.subItem;
    }
}

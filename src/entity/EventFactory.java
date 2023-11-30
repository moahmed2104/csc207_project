package entity;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class EventFactory {
    private HeadItem headItem;
    public Event create(Description description, LocalDateTime start, LocalDateTime end, String parentAddress, HeadItem headItem) throws NoSuchElementException {
        this.headItem = headItem;
        return new Event(description,start,end, headItem.navigate(parentAddress));
        //todo finish writing
    }

}

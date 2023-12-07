package entity;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class EventFactory {
    public Event create(Description description, LocalDateTime start, LocalDateTime end, String parentAddress, HeadItem headItem) throws NoSuchElementException {
        return new Event(description,start,end, headItem.navigate(parentAddress));
    }

}

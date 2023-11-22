package entity;

import java.time.LocalDateTime;

public class EventFactory {
    public Event create(Description description, LocalDateTime start, LocalDateTime end, address) {
        return new Event(description,start,end);
        //todo finish writing
    }

}

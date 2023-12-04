package entity;

import java.time.LocalDateTime;

public class EventFactory {
    private headItem headItem;
    public Event create(Description description, LocalDateTime start, LocalDateTime end, String parentAddress) throws Exception {
        return new Event(description, start, end, headItem.navigate(parentAddress));
        //todo finish writing
    }

}

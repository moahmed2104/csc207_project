package api;

import entity.Event;
import entity.Folder;

import java.time.LocalDateTime;
import java.util.List;

public interface Calendar {
    List<String> getCalendars();
    void insertCalendar(Folder folder);
    List<Event> getEvents(String CalendarID, LocalDateTime start, LocalDateTime end);
    void insertEvent(String CalendarID, Event event);
    void deleteEvent(String CalendarID, Event event);
    void updateEvent(String CalendarID, Event event);

}

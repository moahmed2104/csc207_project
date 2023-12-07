package api;

import entity.Folder;
import entity.Item;

import java.util.HashMap;
import java.util.List;

public interface Calendar {

    String getCalendarID();

    void insertCalendar(Folder folder);
    HashMap<String, List<HashMap<String, String>>> getEvents(String CalendarID);
    void insertItem(String CalendarID, Item item);
    void deleteEvent(String CalendarID, Item item);
    void updateEvent(String CalendarID, Item item);
}

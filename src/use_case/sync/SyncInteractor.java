package use_case.sync;

import api.Calendar;
import entity.*;
import use_case.CreateNewEvent.CreateEventDataAccessInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class SyncInteractor implements SyncInputBoundary{
    final DescriptionFactory descriptionFactory;
    final EventFactory eventFactory;
    final CreateEventDataAccessInterface createEventDataAccessInterface;

    public SyncInteractor(DescriptionFactory descriptionFactory, EventFactory eventFactory,
                          CreateEventDataAccessInterface createEventDataAccessInterface) {
        this.descriptionFactory = descriptionFactory;
        this.eventFactory = eventFactory;
        this.createEventDataAccessInterface = createEventDataAccessInterface;
    }

    @Override
    public void execute(SyncInputData syncInputData) {
        List<Item> localItems = constructLocalItems(syncInputData.getLocalItems(),
                syncInputData.getLocalDescriptions());
        assert localItems != null;
        for (Item item : localItems) {

            if (item instanceof Event){
                compareToCal(item, findItem(item, syncInputData.getRemoteItems().get("events")),
                        syncInputData.getCalendar(), syncInputData.getCalendarID());
            }
            else if (item instanceof Tasks){
                compareToCal(item, findItem(item, syncInputData.getRemoteItems().get("tasks")),
                        syncInputData.getCalendar(), syncInputData.getCalendarID());
            }
        }
    }
    private List<Item> constructLocalItems(HashMap<String, List<HashMap<String, String>>> localItems,
                                           HashMap<String, HashMap<String, String>> descriptions){
        List<HashMap<String, String>> events = localItems.get("events");
        List<Item> items = new ArrayList<>();
        for (HashMap<String, String> event : events) {
            Description description = constructDescription(event.get("description"),
                    descriptions.get(event.get("description")));
            Event e = eventFactory.create(description, LocalDateTime.parse(event.get("startTime")),
                    LocalDateTime.parse(event.get("endTime")), event.get("parentItem"),
                    createEventDataAccessInterface.getHeadItem());
            items.add(e);
        }
        return items;
    }
    private Description constructDescription(String id, HashMap<String, String> info){
         return descriptionFactory.create(id, info.get("name"), info.get("description"), info.get("address"));
    }
    private Boolean compareItem(Item localItem, HashMap<String, String> remoteItem) {
        if (localItem instanceof Event) {
            if (!LocalDateTime.parse(remoteItem.get("start")).equals(((Event) localItem).getStartTime())) {
                return FALSE;
            } else if (!LocalDateTime.parse(remoteItem.get("end")).equals(((Event) localItem).getEndTime())) {
                return FALSE;
            }
        } else {
            if (!LocalDate.parse(remoteItem.get("date")).equals(LocalDate.parse(((Tasks) localItem).getDate()))) {
                return FALSE;
            }
        }

        if (!remoteItem.get("name").equals(localItem.getDescription().getName())){
            return FALSE;
        } else if (!remoteItem.get("description").equals(localItem.getDescription().getDescription())) {
            return FALSE;
        } else { return TRUE; }
    }

    private HashMap<String, String> findItem(Item item, List<HashMap<String, String>> lst){
        for (HashMap<String, String> stringStringHashMap : lst) {
            if (stringStringHashMap.get("id").equals(item.getDescription().getID())) {
                return stringStringHashMap;
            }
        }
        return null;
    }
    private void compareToCal(Item localItem, HashMap<String, String> remoteItem, Calendar calendar, String CalendarID){
        if (remoteItem == null) {
            calendar.insertItem(CalendarID, localItem);
        }
        else{
            if (!compareItem(localItem, remoteItem)){
                calendar.updateEvent(CalendarID, localItem);
            }
        }
    }
}

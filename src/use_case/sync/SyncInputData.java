package use_case.sync;

import api.Calendar;

import java.util.HashMap;
import java.util.List;

public class SyncInputData{
    final private HashMap<String, List<HashMap<String, String>>> localItems;
    final private HashMap<String, HashMap<String, String>> localDescriptions;
    final private HashMap<String, List<HashMap<String, String>>> remoteItems;
    final private Calendar calendar;
    final private String calendarID;

    public SyncInputData(HashMap<String, List<HashMap<String, String>>> localItems,
                         HashMap<String, HashMap<String, String>> localDescriptions,
                         HashMap<String, List<HashMap<String, String>>> remoteItems,
                         Calendar calendar, String calendarID) {
        this.localItems = localItems;
        this.localDescriptions = localDescriptions;
        this.remoteItems = remoteItems;
        this.calendar = calendar;
        this.calendarID = calendarID;
    }
    HashMap<String, List<HashMap<String, String>>> getLocalItems() { return this.localItems; }
    HashMap<String, HashMap<String, String>> getLocalDescriptions() { return this.localDescriptions; }
    HashMap<String, List<HashMap<String, String>>> getRemoteItems() { return this.remoteItems; }
    Calendar getCalendar(){ return this.calendar; }
    String getCalendarID(){ return this.calendarID; }

}

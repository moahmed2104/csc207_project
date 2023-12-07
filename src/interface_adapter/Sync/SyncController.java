package interface_adapter.Sync;

import api.Calendar;
import use_case.sync.SyncInputBoundary;
import use_case.sync.SyncInputData;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class SyncController {
    final SyncInputBoundary syncInteractor;

    public SyncController(SyncInputBoundary syncInteractor) {
        this.syncInteractor = syncInteractor;
    }
    public void execute(HashMap<String, List<HashMap<String, String>>> localItems,
                        HashMap<String, HashMap<String, String>> localDescriptions,
                        HashMap<String, List<HashMap<String, String>>> remoteItems,
                        Calendar calendar, String calendarID) throws ParseException {
        SyncInputData syncInputData = new SyncInputData(localItems, localDescriptions, remoteItems, calendar, calendarID);
        syncInteractor.execute(syncInputData);
    }
}

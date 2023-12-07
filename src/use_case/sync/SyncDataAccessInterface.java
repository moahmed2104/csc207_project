package use_case.sync;

import api.Calendar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface SyncDataAccessInterface {
    HashMap<String, List<HashMap<String, String>>> getLocalItems() throws FileNotFoundException, IOException;
    HashMap<String, HashMap<String, String>> getLocalDescriptions() throws IOException;
    HashMap<String, List<HashMap<String, String>>> getRemoteItems(Calendar calendar, String CalendarID);

}

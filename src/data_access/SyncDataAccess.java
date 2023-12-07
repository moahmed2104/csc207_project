package data_access;

import api.Calendar;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import use_case.sync.SyncDataAccessInterface;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SyncDataAccess implements SyncDataAccessInterface {
    String[] DESCRIPTION_HEADERS = {"id", "name", "description", "address"};
    String[] ITEM_HEADERS = {"class", "description", "subItems", "parentItem", "startTime", "endTime", "date"};


    @Override
    public HashMap<String, List<HashMap<String, String>>> getLocalItems() throws IOException {
        HashMap<String, List<HashMap<String, String>>> items = new HashMap<>();
        List<HashMap<String, String>> events = new ArrayList<>();
        List<HashMap<String, String>> tasks = new ArrayList<>();
        Reader f = new FileReader("src/data_access/items.csv");
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(ITEM_HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        Iterable<CSVRecord> records = csvFormat.parse(f);
        for (CSVRecord record : records) {
            HashMap<String, String> details = new HashMap<>();
            details.put("description", record.get("description"));
            details.put("subItems", record.get("subItems"));
            details.put("parentItem", record.get("parentItem"));
            if (!(record.get("startTime").isEmpty())) {
                details.put("startTime", record.get("startTime"));
                details.put("endTime", record.get("endTime"));
                events.add(details);
            }
            else {
                details.put("date", record.get("date"));
                tasks.add(details);
            }
        }
        items.put("events", events);
        items.put("tasks", tasks);
        return items;
    }

    @Override
    public HashMap<String, HashMap<String, String>> getLocalDescriptions() throws IOException {
        HashMap<String, HashMap<String, String>> descriptions = new HashMap<>();
        Reader f = new FileReader("src/data_access/description.csv");
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(DESCRIPTION_HEADERS)
                .setSkipHeaderRecord(true)
                .build();
        Iterable<CSVRecord> records = csvFormat.parse(f);
        for (CSVRecord record : records) {
            HashMap<String, String> details = new HashMap<>();
            details.put("name", record.get("name"));
            details.put("description", record.get("description"));
            details.put("address", record.get("address"));
            descriptions.put(record.get("id"), details);
        }
        return descriptions;
    }

    @Override
    public HashMap<String, List<HashMap<String, String>>> getRemoteItems(Calendar calendar, String CalendarID) {
        return calendar.getEvents(CalendarID);
    }
}

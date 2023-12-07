package api;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GoogleCalendar implements Calendar {
    private static final String API_URL = "https://www.googleapis.com/calendar/v3";
    private static final String OAUTH_ID = "ya29.a0AfB_byDQQ5WZM9CLwJXNmFWhS7_rgqeg6fDhFCmXenso48T2YofIqns6jv2TyQLmYdwjDprdoAUgGvvQ7Kww6Wu_sitzME9_zFbcUxON_YLPHmsd-93K3_OHT6empC7kaUOAyqLSofp_cnWjIZjPGVZsGP5f3ueaZtXLaCgYKAUYSARASFQHGX2Miy0ZGBl-5dDJlHZE-gbIrEQ0171";

    @Override
    public String getCalendarID() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("%s/users/me/calendarList", API_URL))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
            JSONArray items = responseBody.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                if (items.getJSONObject(i).getBoolean("primary")) return items.getJSONObject(i).getString("id");
            }
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void insertCalendar(Folder folder) {
        Description desc = folder.getDescription();
        String name = desc.getName();
        String description = desc.getDescription();
        String id = desc.getID();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestbody = new JSONObject();
        requestbody.put("summary", name);
        requestbody.put("description", description);
        requestbody.put("id", id);
        RequestBody body = RequestBody.create(mediaType, requestbody.toString());
        Request request = new Request.Builder()
                .url(String.format("%s/calendars", API_URL))
                .method("POST", body)
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, List<HashMap<String, String>>> getEvents(String CalendarID) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events",
                        API_URL, CalendarID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            HashMap<String, List<HashMap<String, String>>> events = new HashMap<>();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray items = responseBody.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                HashMap<String, String> event = new HashMap<>();
                JSONObject item = items.getJSONObject(i);
                String type = "events";
                if (isAllDay(item)){
                    type = "tasks";
                    event.put("date", item.getJSONObject("start").getString("date"));
                }
                else{
                    type = "events";
                    event.put("start", item.getJSONObject("start").getString("dateTime"));
                    event.put("end", item.getJSONObject("end").getString("dateTime"));
                }
                event.put("name", item.getString("summary"));
                event.put("description", item.getString("description"));
                event.put("id", item.getString("id"));

                try{
                    events.get(type).add(event);
                }
                catch (NullPointerException e){
                    List<HashMap<String, String>> x = new ArrayList<>();
                    x.add(event);
                    events.put(type, x);
                }

            }
            return events;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertItem(String CalendarID, Item item){
        if (item instanceof Event)
        { Event event = (Event) item;
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            HashMap<String, LocalDateTime> start = new HashMap<>();
            HashMap<String, LocalDateTime> end = new HashMap<>();
            start.put("dateTime", event.getStartTime());
            end.put("dateTime", event.getEndTime());
            Description desc = event.getDescription();
            MediaType mediaType = MediaType.parse("application/json");
            JSONObject requestbody = new JSONObject();
            requestbody.put("start", start);
            requestbody.put("end", end);
            requestbody.put("description", desc.getDescription());
            requestbody.put("summary", desc.getName());
            requestbody.put("id", desc.getID());
            RequestBody body = RequestBody.create(mediaType, requestbody.toString());
            Request request = new Request.Builder()
                    .url(String.format("%s/calendars/%s/events", API_URL, CalendarID))
                    .method("POST", body)
                    .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                    .addHeader("Accept", "application/json")
                    .build();
            try {

                client.newCall(request).execute().body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void insertEvent(String CalendarID, Event event)  {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, LocalDateTime> start = new HashMap<>();
        HashMap<String, LocalDateTime> end = new HashMap<>();

        start.put("dateTime", event.getStartTime());
        end.put("dateTime", event.getEndTime());
        insertItemRequest(CalendarID, client, event, start, end);
    }
    private void insertTask(String CalendarID, Tasks task) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        LocalDate date = LocalDate.parse(task.getDate());
        HashMap<String, LocalDateTime> start = new HashMap<>();
        HashMap<String, LocalDateTime> end = new HashMap<>();
        start.put("date", date.atStartOfDay());
        end.put("date", date.atStartOfDay());
        insertItemRequest(CalendarID, client, task, start, end);
    }

    private void insertItemRequest(String CalendarID, OkHttpClient client, Item item,
                                   HashMap<String, LocalDateTime> start, HashMap<String, LocalDateTime> end) {
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestbody = new JSONObject();
        requestbody.put("start", start);
        System.out.println(start);
        requestbody.put("end", end);
        System.out.println(end);
        requestbody.put("description", item.getDescription().getDescription());
        requestbody.put("summary", item.getDescription().getName());
        requestbody.put("id", item.getDescription().getID());
        System.out.println(item.getDescription().getID());
        RequestBody body = RequestBody.create(mediaType, requestbody.toString());
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events", API_URL, CalendarID))
                .method("POST", body)
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        System.out.println(request);
        try {
            client.newCall(request).execute();
            System.out.println(client.newCall(request).execute().body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEvent(String CalendarID, Item item) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = item.getDescription().getID();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, EventID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .delete()
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEvent(String CalendarID, Item item) {
        HashMap<String, String> differences = compareEvent(CalendarID, item);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String itemID = item.getDescription().getID();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestbody = new JSONObject();
        if (differences.containsKey("start")) {
            HashMap<String, LocalDateTime> start = new HashMap<>();
            start.put("dateTime", LocalDateTime.parse(differences.get("start")));
            requestbody.put("start", start);
        }
        if (differences.containsKey("end")) {
            HashMap<String, LocalDateTime> end = new HashMap<>();
            end.put("dateTime", LocalDateTime.parse(differences.get("end")));
            requestbody.put("end", end);
        }
        if (differences.containsKey("date")) {
            HashMap<String, LocalDate> date = new HashMap<>();
            date.put("date", LocalDate.parse(differences.get("date")));
            requestbody.put("date", date);
        }
        if (differences.containsKey("summary")) requestbody.put("summary", differences.get("summary"));
        if (differences.containsKey("description")) requestbody.put("description", differences.get("description"));
        RequestBody body = RequestBody.create(mediaType, requestbody.toString());
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, itemID))
                .put(body)
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean eventExists(String CalendarID, Item item) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = item.getDescription().getID();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, EventID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                return TRUE;
            } else {
                return FALSE;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getEvent(String CalendarID, Item item) {
        if (!eventExists(CalendarID, item)) insertItem(CalendarID, item);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = item.getDescription().getID();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, EventID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            return null;
        }
    }

    private HashMap<String, String> compareEvent(String CalendarID, Item item) {
        JSONObject response = getEvent(CalendarID, item);
        HashMap<String, String> differences = new HashMap<>();
        if (item instanceof Tasks){
            assert response != null;
            LocalDate date = LocalDate.parse(response.getJSONObject("start").getString("date"));
            if (date != LocalDate.parse(((Tasks) item).getDate())){
                differences.put("date", ((Tasks) item).getDate());
            }
        }
        else{
            assert response != null;
            LocalDateTime start = LocalDateTime.parse(response.getJSONObject("start").getString("dateTime"));
            LocalDateTime end = LocalDateTime.parse(response.getJSONObject("end").getString("dateTime"));
            if (start != ((Event) item).getStartTime()) {
                differences.put("start", ((Event) item).getStartTime().toString());
            }
            if (end != ((Event) item).getEndTime()) {
                differences.put("end", ((Event) item).getEndTime().toString());
            }
        }

        if (!response.getString("summary").equals(item.getDescription().getName())) {
            differences.put("summary", item.getDescription().getName());
        }
        if (!response.getString("description").equals(item.getDescription().getDescription())) {
            differences.put("description", item.getDescription().getDescription());
        }
        return differences;
    }

    private Boolean isAllDay(JSONObject event){ return event.getJSONObject("start").has("date"); }
}


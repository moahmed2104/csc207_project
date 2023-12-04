package api;

import entity.Description;
import entity.Event;
import entity.Folder;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GoogleCalendar implements Calendar {
    private static final String API_URL = "https://www.googleapis.com/calendar/v3";
    private static final String OAUTH_ID = System.getenv("OAUTH_ID");

    public static String getOauthId() {
        return OAUTH_ID;
    }

    @Override
    public List<String> getCalendars() {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("%s/users/me/calendarList", API_URL))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            List<String> calendarIDs = new ArrayList<>();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray items = responseBody.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                calendarIDs.add(items.getJSONObject(i).getString("id"));
            }
            return calendarIDs;
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
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> getEvents(String CalendarID, LocalDateTime startTime, LocalDateTime endTime) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events?timeMin=%s&timeMax=%s",
                        API_URL, CalendarID, startTime, endTime))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            List<String> events = new ArrayList<>();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray items = responseBody.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                // if event in datastore then add the event to list, else create event and add to list
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertEvent(String CalendarID, Event event) {
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
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEvent(String CalendarID, Event event) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = event.getDescription().getID();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, EventID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .delete()
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEvent(String CalendarID, Event event) {
        HashMap<String, String> differences = compareEvent(CalendarID, event);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = event.getDescription().getID();
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
        if (differences.containsKey("summary")) requestbody.put("summary", differences.get("summary"));
        if (differences.containsKey("description")) requestbody.put("description", differences.get("description"));
        RequestBody body = RequestBody.create(mediaType, requestbody.toString());
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, EventID))
                .put(body)
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean eventExists(String CalendarID, Event event) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = event.getDescription().getID();
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

    private JSONObject getEvent(String CalendarID, Event event) {
        if (eventExists(CalendarID, event) == FALSE) insertEvent(CalendarID, event);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String EventID = event.getDescription().getID();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events/%s", API_URL, CalendarID, EventID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            return null;
        }
    }

    private HashMap<String, String> compareEvent(String CalendarID, Event event) {
        JSONObject response = getEvent(CalendarID, event);
        HashMap<String, String> differences = new HashMap<>();
        LocalDateTime start = LocalDateTime.parse(response.getJSONObject("start").getString("dateTime"));
        LocalDateTime end = LocalDateTime.parse(response.getJSONObject("end").getString("dateTime"));
        if (!response.getString("summary").equals(event.getDescription().getName())) {
            differences.put("summary", event.getDescription().getName());
        }
        if (!response.getString("description").equals(event.getDescription().getDescription())) {
            differences.put("description", event.getDescription().getDescription());
        }
        if (start != event.getStartTime()) {
            differences.put("start", event.getStartTime().toString());
        }
        if (end != event.getEndTime()) {
            differences.put("end", event.getEndTime().toString());
        }
        return differences;
    }
}


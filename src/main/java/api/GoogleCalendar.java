package api;

import entity.Event;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class GoogleCalendar {
    private static final String API_URL = "https://www.googleapis.com/calendar/v3";
    private static final String OAUTH_ID = System.getenv("OAUTH_ID");
    public static String getOauthId() {
        return OAUTH_ID;
    }

    public static void getCalendars(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("%s/users/me/calendarList", API_URL))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try{
            Response response = client.newCall(request).execute();
            System.out.println(response);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void getEvents(String CalendarID){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events", API_URL, CalendarID))
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try{
            Response response = client.newCall(request).execute();
            System.out.println(response);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertEvent(String CalendarID, Event event){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        HashMap<String, LocalDateTime> start = new HashMap<String, LocalDateTime>();
        HashMap<String, LocalDateTime> end = new HashMap<String, LocalDateTime>();
        LocalDateTime startTime = event.getStartTime();
        LocalDateTime endTime = event.getEndTime();
        start.put("dateTime", startTime);
        end.put("dateTime", endTime);
        String description = event.getDescription().getDescription();
        String name = event.getDescription().getName();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestbody = new JSONObject();
        requestbody.put("start", start);
        requestbody.put("end", end);
        requestbody.put("description", description);
        requestbody.put("summary", name);
        RequestBody body = RequestBody.create(mediaType, requestbody.toString());
        Request request = new Request.Builder()
                .url(String.format("%s/calendars/%s/events", API_URL, CalendarID))
                .method("POST", body)
                .addHeader("Authorization", String.format("Bearer %s", OAUTH_ID))
                .addHeader("Accept", "application/json")
                .build();
        try{
            Response response = client.newCall(request).execute();
            System.out.println(response);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



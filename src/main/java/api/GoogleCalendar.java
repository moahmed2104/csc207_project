package api;

import entity.Event;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

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
    }


    public static void main(String[] args) {
        getCalendars();
        getEvents("hammoudyahmed42@gmail.com");
    }
}



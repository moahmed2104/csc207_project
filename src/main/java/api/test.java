package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class test {
    private static final String API_URL = "https://api.notion.com/v1/users";

    public static Response test(String API_TOKEN) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return(response);
    }

    public static void main(String[] args) {
        System.out.println(test(""));
    }
}



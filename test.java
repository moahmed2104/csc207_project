import java.net.HttpURLConnection;
import java.net.URL;

public class test {
    URL url = new URL("https://api.notion.com/v1/users");
    private static final String API_TOKEN = "secret_GQpC526DPjg1vyz0rfJvDUWNKFmyitcvGNpTDeOxNaE";
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
}

package club.chachy.betterttv.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static final String API_URL = "https://api.betterttv.net/3";
    private static final JsonParser PARSER = new JsonParser(); // Backwards compatibility, Minecraft moment

    public static JsonElement get(String path) {
        try {
            URL url = new URL(API_URL + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "BetterTTV-Emotes/" + Constants.VERSION);
            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                return PARSER.parse(reader);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}

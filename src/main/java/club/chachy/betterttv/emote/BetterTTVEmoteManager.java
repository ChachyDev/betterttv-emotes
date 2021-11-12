package club.chachy.betterttv.emote;

import club.chachy.betterttv.emote.data.Emote;
import club.chachy.betterttv.utils.HttpUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class BetterTTVEmoteManager {
    public static final BetterTTVEmoteManager DEFAULT = new BetterTTVEmoteManager(true);

    private final boolean cache;
    private Map<String, Emote> emoteMap;
    private boolean built;

    public BetterTTVEmoteManager(boolean cache) {
        this.cache = cache;

        if (cache) {
            this.emoteMap = new HashMap<>();
        }
    }

    public static void main() {

    }

    public Map<String, Emote> getEmotes() {
        if (cache) {
            if (!built) {
                rebuild(false);
            }

            return emoteMap;
        }

        return getEmotes0();
    }

    private Map<String, Emote> getEmotes0() {
        Map<String, Emote> emotes = new HashMap<>();

        JsonArray res = HttpUtils.get("/cached/emotes/global").getAsJsonArray();

        for (JsonElement element : res) {
            JsonObject obj = element.getAsJsonObject();
            String code = obj.get("code").getAsString();
            String id = obj.get("id").getAsString();

            emotes.put(code, new Emote(code, id));
        }
        return emotes;
    }

    public void rebuild(boolean force) {
        if (cache && !built || cache && force) {
            emoteMap.clear();
            emoteMap.putAll(getEmotes0());
            built = true;
        } else {
            throw new IllegalStateException("Caching is not enabled, rebuilds can only be ran when caching is enabled.");
        }
    }
}

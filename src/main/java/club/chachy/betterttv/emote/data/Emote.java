package club.chachy.betterttv.emote.data;

public class Emote {
    private final String id;
    private final String code;
    private final EmoteImage emoteImage;

    public Emote(String id, String code) {
        this.id = id;
        this.code = code;
        this.emoteImage = new EmoteImage(id);
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public EmoteImage getEmoteImage() {
        return emoteImage;
    }
}
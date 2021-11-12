package club.chachy.betterttv.emote.data;

public class EmoteImage {
    public static final String SMALL_IMAGE_SIZE = "1x";
    public static final String MEDIUM_IMAGE_SIZE = "2x";
    public static final String LARGE_IMAGE_SIZE = "3x";

    public static final String CDN_ENDPOINT = "https://cdn.betterttv.net";

    private final String emoteId;

    public EmoteImage(String emoteId) {
        this.emoteId = emoteId;
    }

    public String getSmallImage() {
        return getImage(SMALL_IMAGE_SIZE);
    }

    public String getMediumImage() {
        return getImage(MEDIUM_IMAGE_SIZE);
    }

    public String getLargeImage() {
        return getImage(LARGE_IMAGE_SIZE);
    }

    private String getImage(String size) {
        if (!size.equals(SMALL_IMAGE_SIZE) && !size.equals(MEDIUM_IMAGE_SIZE) && !size.equals(LARGE_IMAGE_SIZE)) {
            throw new IllegalStateException(String.format("Unknown size (choose between %s, %s and %s)", SMALL_IMAGE_SIZE, MEDIUM_IMAGE_SIZE, LARGE_IMAGE_SIZE));
        }

        return String.format("%s/emote/%s/%s", CDN_ENDPOINT, emoteId, size);
    }
}

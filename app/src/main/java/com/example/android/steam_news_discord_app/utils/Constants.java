package com.example.android.steam_news_discord_app.utils;

import com.example.android.steam_news_discord_app.models.AllItemsPerGame;
import com.example.android.steam_news_discord_app.models.Game;
import com.example.android.steam_news_discord_app.models.News;
import com.example.android.steam_news_discord_app.models.ItemAvgPrices;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    /**
     * Create a private constructor because no one should ever create a {@link Constants} object.
     */
    private Constants() {
    }

    /**  Extract the key associated with the JSONObject */
    static final String JSON_KEY_RESPONSE = "response";
    static final String JSON_KEY_RESULTS = "results";
    static final String JSON_KEY_WEB_TITLE = "webTitle";
    static final String JSON_KEY_SECTION_NAME = "sectionName";
    static final String JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    static final String JSON_KEY_WEB_URL = "webUrl";
    static final String JSON_KEY_TAGS = "tags";
    static final String JSON_KEY_FIELDS = "fields";
    static final String JSON_KEY_THUMBNAIL = "thumbnail";
    static final String JSON_KEY_TRAIL_TEXT = "trailText";

    /** API Key */
    public static final String EXTERNAL_API_KEY = "4lJLVKdTtAO6pYsGv7thIc3S_2g";
    public static final String STEAM_API_KEY = "7648647C000658663FEC746BE49F8522";

    /** Default number to set the image on the top of the textView */
    public static final int DEFAULT_NUMBER = 0;

    /** Constants value for each fragment */
    public static final int HOME_STEAM_NEWS = 0;
    public static final int STEAM_TRENDS = 1;
    public static final int DISCORD_BOT = 2;

    public static String CURRENT_USER_STEAM_ID = "";
    public static List<Game> CURRENT_USER_APP_IDS = new ArrayList<Game>();

    public static List<News> CURRENT_NEWS = new ArrayList<News>();

    public static List<ItemAvgPrices> ITEM_AVG_PRICES = new ArrayList<ItemAvgPrices>();

    public static List<AllItemsPerGame> ALL_ITEMS_PER_GAME = new ArrayList<AllItemsPerGame>();
}

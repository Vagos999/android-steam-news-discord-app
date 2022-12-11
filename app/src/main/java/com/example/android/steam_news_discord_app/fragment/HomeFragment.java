package com.example.android.steam_news_discord_app.fragment;

import static com.example.android.steam_news_discord_app.utils.Constants.CURRENT_USER_APP_IDS;
import static com.example.android.steam_news_discord_app.utils.Constants.EXTERNAL_API_KEY;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.android.steam_news_discord_app.R;
import com.example.android.steam_news_discord_app.models.Game;
import com.example.android.steam_news_discord_app.utils.Constants;
import com.example.android.steam_news_discord_app.utils.JSONAsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * The HomeFragment is a {@link BaseFragment} subclass that
 * reuses methods of the parent class
 */
public class HomeFragment extends BaseFragment {

    public static final String TAG = HomeFragment.class.getName();

    TextView titleView, authorView, dateView, contentView;
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View homeView = inflater.inflate(R.layout.content_home_steam_news, container, false);

        imageView = homeView.findViewById(R.id.news_image);
        try {
            getAllItemsPerGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONAsyncTask jsonAsyncTask = new JSONAsyncTask(homeView, getActivity());
        jsonAsyncTask.execute("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=7648647C000658663FEC746BE49F8522&steamid="+Constants.CURRENT_USER_STEAM_ID+"&format=json&include_appinfo=true");

        int[] num = {440, 10, 300, 40, 50, 70, 219540};

        Random random = new Random();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int i = num[random.nextInt(6)];

            JSONAsyncTask jsonAsyncTask1 = new JSONAsyncTask(homeView, getActivity());
            jsonAsyncTask1.execute("https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=" + i + "&count=1");
        }

//        switch (j){
//            case 440:
//                imageView.setImageDrawable(fortress_image);
//        }

        Pattern regex = Pattern.compile("<[^>]*>");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        titleView = homeView.findViewById(R.id.news_title);
        authorView = homeView.findViewById(R.id.news_author);
        dateView = homeView.findViewById(R.id.news_date);
        contentView = homeView.findViewById(R.id.news_content);

//        titleView.setText(Constants.CURRENT_NEWS.get(0).getTitle());
//        authorView.setText("By " + Constants.CURRENT_NEWS.get(0).getAuthor());
//        dateView.setText(Constants.CURRENT_NEWS.get(0).getDate().toString());
//        contentView.setText(regex.matcher(Constants.CURRENT_NEWS.get(0).getTrailTextHtml()).replaceAll(""));

        return homeView;
    }

    public void getAllItemsPerGame() throws IOException {
//        for(Game game : CURRENT_USER_APP_IDS){
//            JSONAsyncTask jsonAsyncTask = new JSONAsyncTask();
//            jsonAsyncTask.execute("https://api.steamapis.com/market/items/440?api_key=" + EXTERNAL_API_KEY);
//        }
        JSONAsyncTask jsonAsyncTask = new JSONAsyncTask();
        jsonAsyncTask.execute("https://api.steamapis.com/market/items/440?api_key=" + EXTERNAL_API_KEY);
    }
}

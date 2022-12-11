package com.example.android.steam_news_discord_app.fragment;

import static com.example.android.steam_news_discord_app.utils.Constants.ALL_ITEMS_PER_GAME;
import static com.example.android.steam_news_discord_app.utils.Constants.CURRENT_USER_APP_IDS;
import static com.example.android.steam_news_discord_app.utils.Constants.EXTERNAL_API_KEY;
import static com.example.android.steam_news_discord_app.utils.Constants.ITEM_AVG_PRICES;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.android.steam_news_discord_app.R;
import com.example.android.steam_news_discord_app.models.Game;
import com.example.android.steam_news_discord_app.utils.JSONAsyncTask;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * The SteamTrendsFragment is a {@link BaseFragment} subclass that
 * reuses methods of the parent class by passing the specific type of article to be fetched.
 */
public class SteamTrendsFragment extends BaseFragment {

    private static final String TAG = SteamTrendsFragment.class.getName();

    Spinner gameNameSpinner, itemNameSpinner;
    private LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries = new ArrayList<>();;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View homeView = inflater.inflate(R.layout.content_steam_trends, container, false);

        gameNameSpinner = homeView.findViewById(R.id.game_name_spinner);
        itemNameSpinner = homeView.findViewById(R.id.item_name_spinner);

        ArrayList<String> gameNameArrayList = new ArrayList<>();
        ArrayList<String> itemsNameArrayList = new ArrayList<>();

        for(int i = 0; i < CURRENT_USER_APP_IDS.size(); i++) {
            gameNameArrayList.add(CURRENT_USER_APP_IDS.get(i).getName());
        }

        for(int i = 0; i < ALL_ITEMS_PER_GAME.size(); i++) {
            itemsNameArrayList.add(ALL_ITEMS_PER_GAME.get(i).getItemName());
        }

        ArrayAdapter gameNameAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, gameNameArrayList);
        gameNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameNameSpinner.setAdapter(gameNameAdapter);
        gameNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gameName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        ArrayAdapter itemNameAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item, itemsNameArrayList);
        itemNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemNameSpinner.setAdapter(itemNameAdapter);
        itemNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        return homeView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            connectToSteam();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lineChart = getView().findViewById(R.id.steam_trends_graph);

        getEntries();

        lineDataSet = new LineDataSet(lineEntries, "");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);
    }

    public void connectToSteam() throws IOException{
        JSONAsyncTask jsonAsyncTask = new JSONAsyncTask();
        jsonAsyncTask.execute("https://api.steamapis.com/market/item/440/Festivized Killstreak Airwolf Knife (Field-Tested)?api_key=" + EXTERNAL_API_KEY );
    }



    private void getEntries() {
//        final ArrayList<String> xLabels = new ArrayList<>();
//        final ArrayList<Integer> yLabels = new ArrayList<>();
//
        for(int i = 0; i < ITEM_AVG_PRICES.size(); i++) {
//            xLabels.add(ITEM_AVG_PRICES.get(i).getDate());
//            yLabels.add(ITEM_AVG_PRICES.get(i).getCount());
            lineEntries.add(new Entry(i, (ITEM_AVG_PRICES.get(i).getCount())));
        }
    }


}

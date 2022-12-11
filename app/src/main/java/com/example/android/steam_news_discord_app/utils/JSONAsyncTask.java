package com.example.android.steam_news_discord_app.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.steam_news_discord_app.models.Game;
import com.example.android.steam_news_discord_app.models.News;
import com.example.android.steam_news_discord_app.models.AllItemsPerGame;
import com.example.android.steam_news_discord_app.models.ItemAvgPrices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

    View view;
    Activity activity;

    public JSONAsyncTask(View view, Activity activity){
        this.view=view;
        this.activity=activity;
    }

    public JSONAsyncTask() {

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            System.out.println("Getting response");


            int status = con.getResponseCode();

            while (status > 200) {
                if(status ==400){
                    break;
                }

                System.out.println("Getting response code");
                status = con.getResponseCode();
            }

            if (status == 200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                if(urls[0].contains("GetOwnedGames")){
                    parseAppIdsFromResponse(content);
                }else if(urls[0].contains("GetNewsForApp")){
                    parseNewsFromResponse(content);
                } else if(urls[0].contains("items/")){
                    if (!(status == 400)){
                        parseAllItemsPerGameFromResponse(content);
                    }
                } else if(urls[0].contains("item/")){
                    parseItemAveragePriceFromResponse(content);
                }
                in.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void parseAppIdsFromResponse(StringBuffer content) throws JSONException {
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONObject responseJson = jsonObject.getJSONObject("response");
        JSONArray gamesJson = responseJson.getJSONArray("games");

        Constants.CURRENT_USER_APP_IDS = new ArrayList<Game>();

        for(int i = 0; i < gamesJson.length(); i++){
            JSONObject appJson = (JSONObject) gamesJson.get(i);
            Constants.CURRENT_USER_APP_IDS.add(new Game((String) appJson.get("name"),Integer.toString((Integer) appJson.get("appid"))));
        }
    }

    public void parseNewsFromResponse(StringBuffer content) throws JSONException {
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONObject responseJson = jsonObject.getJSONObject("appnews");
        JSONArray newsJson = responseJson.getJSONArray("newsitems");

        for (int i = 0; i < newsJson.length(); i++) {
            JSONObject appJson = (JSONObject) newsJson.get(i);
            Constants.CURRENT_NEWS.add(new News((String) appJson.get("title"), (String) appJson.get("author"), (Integer) appJson.get("date"), (String) appJson.get("url"), (String) appJson.get("contents")));

//            LinearLayout linearLayout = this.view.findViewById(R.id.news);
//            EditText editText = new EditText(this.activity);
//
//            editText.setId(i); //Set id to remove in the future.
//            editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
//            editText.setText(Constants.CURRENT_NEWS.get(i).getTrailTextHtml());
//            try{
//                linearLayout.addView(editText);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
        }
    }

    public void parseAllItemsPerGameFromResponse(StringBuffer content) throws JSONException {
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray responseJson = jsonObject.getJSONArray("data");

        Constants.ALL_ITEMS_PER_GAME = new ArrayList<AllItemsPerGame>();

        for(int i = 0; i < 10; i++){
            JSONObject appJson = (JSONObject) responseJson.get(i);
            Constants.ALL_ITEMS_PER_GAME.add(new AllItemsPerGame((String) appJson.get("market_hash_name")));
        }
    }

    public void parseItemAveragePriceFromResponse(StringBuffer content) throws JSONException {
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray medianAvgPrices15daysJson = jsonObject.getJSONArray("median_avg_prices_15days");

        Constants.ITEM_AVG_PRICES = new ArrayList<ItemAvgPrices>();

        for(int i = 0; i < medianAvgPrices15daysJson.length(); i++){
            JSONArray appJson = (JSONArray) medianAvgPrices15daysJson.get(i);
            Constants.ITEM_AVG_PRICES.add(new ItemAvgPrices((String) appJson.get(0), (Integer) appJson.get(2)));
        }
    }

    protected void onPostExecute(Boolean result) {

    }
}

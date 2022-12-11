package com.example.android.steam_news_discord_app.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.android.steam_news_discord_app.R;
import com.example.android.steam_news_discord_app.fragment.HomeFragment;
import com.example.android.steam_news_discord_app.models.Game;
import com.example.android.steam_news_discord_app.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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

        for(int i = 0; i < gamesJson.length(); i++){
            JSONObject appJson = (JSONObject) gamesJson.get(i);
            Constants.CURRENT_USER_APP_IDS.add(new Game((String) appJson.get("name"),Integer.toString((Integer) appJson.get("appid"))));
            if(i < 3) {
                JSONAsyncTask jsonAsyncTask = new JSONAsyncTask(this.view, this.activity);
                jsonAsyncTask.execute("https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=" + Integer.toString((Integer) appJson.get("appid")) + "&count=1");
            }
        }
    }

    public void parseNewsFromResponse(StringBuffer content) throws JSONException {
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONObject responseJson = jsonObject.getJSONObject("appnews");
        JSONArray newsJson = responseJson.getJSONArray("newsitems");

        for(int i = 0; i < newsJson.length(); i++){
            JSONObject appJson = (JSONObject) newsJson.get(i);
            Constants.CURRENT_NEWS.add(new News((String) appJson.get("title"),(String) appJson.get("author"),(Integer) appJson.get("date"),(String) appJson.get("url"),(String) appJson.get("contents")));

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

    protected void onPostExecute(Boolean result) {

    }
}

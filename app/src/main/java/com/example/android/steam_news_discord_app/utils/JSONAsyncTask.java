package com.example.android.steam_news_discord_app.utils;

import android.os.AsyncTask;

import com.example.android.steam_news_discord_app.models.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


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
        }
    }

    protected void onPostExecute(Boolean result) {

    }
}

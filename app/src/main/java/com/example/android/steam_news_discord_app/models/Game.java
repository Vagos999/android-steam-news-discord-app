package com.example.android.steam_news_discord_app.models;

public class Game {

    public String name;
    public String appId;

    public Game(String name, String appId){
        this.name = name;
        this.appId = appId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getAppId(){
        return this.appId;
    }

    public void setAppId(String appId){
        this.appId = appId;
    }
}

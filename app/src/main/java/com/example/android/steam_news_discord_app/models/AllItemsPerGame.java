package com.example.android.steam_news_discord_app.models;

public class AllItemsPerGame {
    public String itemName;

    public AllItemsPerGame(String itemName){
        this.itemName = itemName;
    }

    public String getItemName(){
        return this.itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }
}

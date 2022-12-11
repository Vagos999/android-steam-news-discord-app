package com.example.android.steam_news_discord_app.models;

public class ItemAvgPrices {
    public String date;
    public Integer count;

    public ItemAvgPrices(String date, Integer count){
        this.date = date;
        this.count = count;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Integer getCount(){
        return this.count;
    }

    public void setCount(Integer count){
        this.count = count;
    }
}

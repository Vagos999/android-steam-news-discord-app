/*
 * MIT License
 *
 * Copyright (c) 2018 Soojeong Shin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.android.steam_news_discord_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.android.steam_news_discord_app.R;
import com.example.android.steam_news_discord_app.models.Game;
import com.example.android.steam_news_discord_app.utils.Constants;
import com.example.android.steam_news_discord_app.utils.JSONAsyncTask;

/**
 * The HomeFragment is a {@link BaseArticlesFragment} subclass that
 * reuses methods of the parent class
 */
public class HomeFragment extends BaseArticlesFragment {

    public static final String TAG = HomeFragment.class.getName();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View homeView = inflater.inflate(R.layout.content_home_steam_news, container, false);

        JSONAsyncTask jsonAsyncTask = new JSONAsyncTask(homeView, getActivity());
        jsonAsyncTask.execute("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=7648647C000658663FEC746BE49F8522&steamid="+Constants.CURRENT_USER_STEAM_ID+"&format=json&include_appinfo=true");


        return homeView;
    }
}

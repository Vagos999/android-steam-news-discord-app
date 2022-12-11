package com.example.android.steam_news_discord_app.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.android.steam_news_discord_app.R;

/**
 * The WorldFragment is a {@link BaseFragment} subclass that
 * reuses methods of the parent class by passing the specific type of article to be fetched.
 */
public class DiscordBotFragment extends BaseFragment {

    private static final String TAG = DiscordBotFragment.class.getName();

    Uri uri = Uri.parse("https://discord.com/api/oauth2/authorize?client_id=783882982817529856&permissions=8&scope=bot%20applications.commands");
    Intent intent = new Intent(Intent.ACTION_VIEW, uri);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View homeView = inflater.inflate(R.layout.content_discord_bot, container, false);

        return homeView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView discordBotPlus = getView().findViewById(R.id.discord_bot_plus);

        discordBotPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}

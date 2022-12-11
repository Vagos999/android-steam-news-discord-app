package com.example.android.steam_news_discord_app.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.steam_news_discord_app.EmptyRecyclerView;
import com.example.android.steam_news_discord_app.R;

/**
 * The BaseArticlesFragment is a {@link Fragment} subclass that implements the LoaderManager.LoaderCallbacks
 * interface in order for Fragment to be a client that interacts with the LoaderManager. It is
 * base class that is responsible for displaying a set of articles, regardless of type.
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find a reference to the {@link RecyclerView} in the layout
        // Replaced RecyclerView with EmptyRecyclerView
        EmptyRecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);

        // Set the layoutManager on the {@link RecyclerView}
        mRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }


    /**
     * When the user returns to the previous screen by pressing the up button in the SettingsActivity,
     * restart the Loader to reflect the current value of the preference.
     */
    @Override
    public void onResume() {
        super.onResume();
    }
}

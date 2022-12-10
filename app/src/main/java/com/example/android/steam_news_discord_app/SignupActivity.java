package com.example.android.steam_news_discord_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView signUpTextView = findViewById(R.id.lead_to_sign_in);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSigninActivity();
            }
        });

        Button continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSigninActivity();
            }
        });
    }

    public void openSigninActivity(){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
    }

}

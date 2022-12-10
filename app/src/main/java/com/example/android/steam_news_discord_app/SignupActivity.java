package com.example.android.steam_news_discord_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.steam_news_discord_app.utils.DBHandler;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameEdt, passwordEdt, steamIdEdt;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // initializing all our variables.
        usernameEdt = findViewById(R.id.username_editText);
        passwordEdt = findViewById(R.id.password_editText);
        steamIdEdt = findViewById(R.id.steam_user_id_editText);

        steamIdEdt.setText("76561199028406354");


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(SignupActivity.this);

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

                // below line is to get data from all edit text fields.
                String username = usernameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                String steamId = steamIdEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (username.isEmpty() && password.isEmpty() && steamId.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please fill all the data...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(dbHandler.checkUser(username)){
                    Toast.makeText(SignupActivity.this, "Username already exists!", Toast.LENGTH_LONG).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewUser(username, password, steamId);

                // after adding the data we are displaying a toast message.
                Toast.makeText(SignupActivity.this, "User has been added.", Toast.LENGTH_SHORT).show();

                openSigninActivity();
            }
        });
    }

    public void openSigninActivity(){
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
    }

}

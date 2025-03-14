package com.example.android.steam_news_discord_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.steam_news_discord_app.utils.Constants;
import com.example.android.steam_news_discord_app.utils.DBHandler;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SigninActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginManager loginManager;
    private EditText usernameEdt, passwordEdt;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // initializing all our variables.
        usernameEdt = findViewById(R.id.username_editText);
        passwordEdt = findViewById(R.id.password_editText);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(SigninActivity.this);

        TextView signUpTextView = findViewById(R.id.lead_to_sign_up);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

        AccessToken accessToken = AccessToken . getCurrentAccessToken ();
        boolean isLoggedIn = accessToken != null && ! accessToken.isExpired ();

        Button signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String username = usernameEdt.getText().toString();
                String password = passwordEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (username.isEmpty() && password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please fill all the data...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(dbHandler.checkIfCredentialsAreCorrect(username,password)){
                    Toast.makeText(SigninActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                    Constants.CURRENT_USER_STEAM_ID = dbHandler.getCurrentUserSteamId(username);
                    openHomeActivity();
                }else{
                    Toast.makeText(SigninActivity.this, "Wrong credentials! Signup first?", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        final Button facebookLoginButton = (Button) findViewById(R.id.facebookLoginButton);

        //Facebook login
        FacebookSdk.sdkInitialize(SigninActivity.this);
        callbackManager = CallbackManager.Factory.create();
        facebookLogin();

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginManager.logInWithReadPermissions(
                        SigninActivity.this,
                        Arrays.asList(
                                "email",
                                "public_profile",
                                "user_birthday"));
            }
        });
    }

    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void facebookLogin()
    {
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        if (object != null) {
                            try {
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String fbUserID = object.getString("id");

                                disconnectFromFacebook();

                                // do action after Facebook login success
                                // or call your API
                            }
                            catch (NullPointerException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString(
                        "fields",
                        "id, name, email, gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel()
            {
                Log.v("LoginScreen", "---onCancel");
            }

            @Override
            public void onError(FacebookException error)
            {
                // here write code when get error
                Log.v("LoginScreen", "----onError: " + error.getMessage());
            }
        });
    }

    public void disconnectFromFacebook()
    {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse)
                    {
                        LoginManager.getInstance().logOut();
                    }
                })
                .executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // add this line
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
}

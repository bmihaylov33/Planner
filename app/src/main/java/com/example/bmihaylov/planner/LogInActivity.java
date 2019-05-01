package com.example.bmihaylov.planner;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URL;
import java.util.Arrays;

public class LogInActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView textViewName, textViewEmail;
    private static final String EMAIL = "email";

    private FirebaseAuth auth;
    private FirebaseUser user;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null) {
            setContentView(R.layout.activity_log_in);
            FacebookSdk.sdkInitialize(getApplicationContext());

            textViewName = findViewById(R.id.textName);
            textViewEmail = findViewById(R.id.textEmail);
            loginButton = findViewById(R.id.login_button);
            callbackManager = CallbackManager.Factory.create();
            loginButton.setReadPermissions(Arrays.asList(EMAIL));


        } else {
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            String name = auth.getCurrentUser().getDisplayName().toString();
            String email = auth.getCurrentUser().getEmail().toString();
            String photoURL = auth.getCurrentUser().getPhotoUrl().toString();
            bundle.putString("USER_NAME", name);
            bundle.putString("USER_EMAIL", email);
            bundle.putString("USER_PHOTO_URL", photoURL);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void buttonClickLoginFb(View v) {
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(),"User canceled it!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {

                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LogInActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                String email = authResult.getUser().getEmail().toString();
                //textViewEmail.setText(email);
                String name = authResult.getUser().getDisplayName().toString();
                //textViewName.setText(name);

                bundle.putString("USER_NAME", name);
                bundle.putString("USER_EMAIL", email);

                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
//                ProfileFragment profileFragment = new ProfileFragment();
//                profileFragment.setArguments(bundle);
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
            }
        });
    }
}

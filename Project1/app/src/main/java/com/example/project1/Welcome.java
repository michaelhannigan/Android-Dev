package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

//This class welcomes users once they are signed in
public class Welcome extends AppCompatActivity {

    private TextView welcomeUsername;

    // creates the activity and all function calls are done when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // gets the data from the main activity through the intent and gives the user a message
        // based on their username
        welcomeUsername = findViewById(R.id.welcome);
        Intent intent = getIntent();
        String message = "Welcome " + intent.getStringExtra("Info") + "!";
        welcomeUsername.setText(message);

    }
}
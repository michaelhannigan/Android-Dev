package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Data data;
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private Button btnSignUp;
    private static boolean beenClicked;

    // creates the activity and all function calls are done when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing all of the textviews and buttons
        data = new Data();
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        beenClicked = false;

        // This listener for the signup button it sends an intent to the signup activity and
        // sends the user there as well.
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beenClicked = true;
                Intent signUpIntent = new Intent(getApplicationContext(), SignUp.class);
                signUpIntent.putExtra("Data", data);
                startActivity(signUpIntent);

            }
        });

        // this listener for the login button validates the data that the user inputs and
        // sends the data to the welcome activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();


                Intent intent = getIntent();
                if(intent.getExtras() != null)
                    data = (Data) intent.getSerializableExtra("Data");

                if(!data.CheckUsername(usernameText)) {
                    Toast.makeText(getApplicationContext(), "Username doesn't exist",
                            Toast.LENGTH_LONG).show();
                }
                else if(!data.CheckCredentials(usernameText, passwordText)){
                    Toast.makeText(getApplicationContext(), "Password is incorrect",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent welcomeIntent = new Intent(getApplicationContext(), Welcome.class);
                    welcomeIntent.putExtra("Info", usernameText);
                    startActivity(welcomeIntent);
                }
            }
        });

        


    }


}

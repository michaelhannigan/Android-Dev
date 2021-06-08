package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//imports the validation api
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

//This activity signs up users with the data that they input into the required fields
public class SignUp extends AppCompatActivity {

    private EditText username, password, retypePassword, email, phone;
    private Button btnSignUp;
    private AwesomeValidation awesomeValidation;

    // creates the activity and all function calls are done when activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //initializes validation object
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //initializing all of the textviews and buttons
        username = findViewById(R.id.PersonName);
        password = findViewById(R.id.LoginPassword);
        retypePassword = findViewById(R.id.ReTypeLoginPassword);
        email = findViewById(R.id.Email);
        phone= findViewById(R.id.PhoneNumber);
        btnSignUp = findViewById(R.id.btnSignMeUp);


        //adds validation instances to the validation object
        awesomeValidation.addValidation(this, R.id.Email, Patterns.EMAIL_ADDRESS,
                R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.PhoneNumber,
                "^[+]?[0-9]{10,13}$", R.string.invalid_phone);

        //this listner for the signup button checks to validate all of the fields.
        //if all are validated it sends an intent and the data back to the main activity
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if it passes the validation instances created
                if (awesomeValidation.validate()) {

                        //assigns all the text in the fields to variables
                        String usernameText = username.getText().toString();
                        String passwordText = password.getText().toString();
                        String retypePasswordText = retypePassword.getText().toString();
                        String emailText = email.getText().toString();
                        String phoneText = phone.getText().toString();

                        //recieves the intent from the main activity
                        Intent intent = getIntent();
                        Data data = (Data) intent.getSerializableExtra("Data");

                        //checking to make sure all fields are filled, the passwords match, and
                        //the username is unique
                        if (usernameText.equals("") || passwordText.equals("") || retypePasswordText.equals("") ||
                                emailText.equals("") || phoneText.equals("")) {
                            Toast.makeText(getApplicationContext(), "You must fill out all of the fields",
                                    Toast.LENGTH_LONG).show();
                        } else if (data.CheckUsername(usernameText)) {
                            Toast.makeText(getApplicationContext(), "Username already exists",
                                    Toast.LENGTH_LONG).show();
                        } else if (!retypePasswordText.equals(passwordText)) {
                            Toast.makeText(getApplicationContext(), "Passwords do not match",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            data.AddCredential(usernameText, passwordText);
                            Toast.makeText(getApplicationContext(), "Your account has been created",
                                    Toast.LENGTH_LONG).show();

                            //sends the data back to the main activity
                            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                            mainIntent.putExtra("Data", data);
                            startActivity(mainIntent);
                        }

                }
            }
        });

    }
}
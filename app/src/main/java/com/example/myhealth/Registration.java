package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText registrationName;
    EditText registrationUsername;
    EditText registrationPassword;
    Button registrationButton;
    String name;
    String username;
    String password;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        registrationName = (EditText) findViewById(R.id.registration_name);
        registrationUsername = (EditText) findViewById(R.id.registration_username);
        registrationPassword = (EditText) findViewById(R.id.registration_password);
        registrationButton = (Button) findViewById(R.id.registration_button);
    }

    public void registerUser(View view) {
        name = registrationName.getText().toString().trim();
        username = registrationUsername.getText().toString().trim();
        password = registrationPassword.getText().toString().trim();
        database = new DBHelper(this);
        if (name.length() == 0 || username.length() == 0 || password.length() == 0) {
            Toast.makeText(this, "Ве молиме пополнете ги сите полиња!", Toast.LENGTH_LONG).show();
        } else if(database.checkIfUsernameExists(username)){
            Toast.makeText(this, "Корисничкото име веќе постои!", Toast.LENGTH_LONG).show();
            registrationUsername.setText("");
        } else if(password.length()<8) {
            Toast.makeText(this, "Лозинката мора да содржи минимум 8 карактери!", Toast.LENGTH_LONG).show();
            registrationPassword.setText("");
        } else {
            database.addNewUser(name, username, password);
            Toast.makeText(this, "Успешна регистрација!", Toast.LENGTH_LONG).show();
        }
    }
}
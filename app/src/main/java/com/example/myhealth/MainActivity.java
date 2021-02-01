package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPassword;
    Button loginButton;
    Button registerButton;
    String name;
    String username;
    String password;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loginUsername = (EditText) findViewById(R.id.login_username);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_button);
    }
    public void logIn(View view) {
        if(loginUsername.getText().toString().trim().length() == 0 || loginPassword.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Ве молиме пополнете ги сите полиња!", Toast.LENGTH_LONG).show();
        } else {
            username = loginUsername.getText().toString().trim();
            password = loginPassword.getText().toString().trim();
            database = new DBHelper(this);
            if(!database.checkIfUsernameExists(username)) {
                Toast.makeText(this, "Внесеното корисничко име не постои", Toast.LENGTH_LONG).show();
                loginUsername.setText("");
                loginPassword.setText("");
            } else {
                if(password.equals(database.returnPassword(username))) {
                    name = database.returnName(username);
                    Intent intent = new Intent(this, Home.class);
                    intent.putExtra("USERNAME", username);
                    intent.putExtra("NAME", name);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Погрешна лозинка!", Toast.LENGTH_LONG).show();
                    loginPassword.setText("");
                }
            }
        }
    }
    public void register(View view) {
        Intent intent1 = new Intent(this, Registration.class);
        startActivity(intent1);
    }
}
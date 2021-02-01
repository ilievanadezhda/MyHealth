package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class Home extends AppCompatActivity {
    String name;
    String username;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
    }

    public void openHowAreYou(View view) {
        Calendar rightNow = Calendar.getInstance();
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int year = rightNow.get(Calendar.YEAR);
        String date = day + "/" + month + "/" + year;
        database = new DBHelper(this);
        if (database.checkIfDailyReportExists(username, date)) {
            Toast.makeText(this, "Веќе постои дневен извештај! Обидете се повторно утре!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, HowAreYou.class);
            intent.putExtra("NAME", name);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        }
    }

    public void openAddMedication(View view) {
        Intent intent = new Intent(this, AddMedication.class);
        intent.putExtra("NAME", name);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void openMyMedications(View view) {
        Intent intent = new Intent(this, MyMedications.class);
        intent.putExtra("NAME", name);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void openOldReports(View view) {
        Intent intent = new Intent(this, OldReports.class);
        intent.putExtra("NAME", name);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void openReminder(View view) {
        Intent intent = new Intent(this, Reminder.class);
        intent.putExtra("NAME", name);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }
    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
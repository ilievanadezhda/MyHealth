package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Reminder extends AppCompatActivity {
    String name;
    String username;
    DBHelper database;
    TextView dayWeek;
    TextView morning_input;
    TextView noon_input;
    TextView evening_input;
    Button button;
    ImageButton morningbutton;
    ImageButton noonbutton;
    ImageButton eveningbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        database = new DBHelper(this);
        Calendar rightNow = Calendar.getInstance();
        final int day_of_week = rightNow.get(Calendar.DAY_OF_WEEK);
        dayWeek = (TextView) findViewById(R.id.day_of_week);
        switch (day_of_week) {
            case 1:
                dayWeek.setText("Недела");
                break;
            case 2:
                dayWeek.setText("Понеделник");
                break;
            case 3:
                dayWeek.setText("Вторник");
                break;
            case 4:
                dayWeek.setText("Среда");
                break;
            case 5:
                dayWeek.setText("Четврток");
                break;
            case 6:
                dayWeek.setText("Петок");
                break;
            case 7:
                dayWeek.setText("Сабота");
                break;
        }
        List<List<String>> morning = database.getMedications(username, day_of_week, 1);
        morning_input = (TextView) findViewById(R.id.remindar_morning_input);
        morning_input.setText(formatTextAsIWish(morning));
        List<List<String>> noon = database.getMedications(username, day_of_week, 2);
        noon_input = (TextView) findViewById(R.id.remindar_noon_input);
        noon_input.setText(formatTextAsIWish(noon));
        List<List<String>> evening = database.getMedications(username, day_of_week, 3);
        evening_input = (TextView) findViewById(R.id.remindar_evening_input);
        evening_input.setText(formatTextAsIWish(evening));

        button = (Button) findViewById(R.id.reminder_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), Home.class);
                intent1.putExtra("USERNAME", username);
                intent1.putExtra("NAME", name);
                startActivity(intent1);
            }
        });
        morningbutton = (ImageButton) findViewById(R.id.morning_button);
        morningbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.decrement(username, day_of_week, 1);
                Toast.makeText(v.getContext(), "Успех!", Toast.LENGTH_LONG).show();

            }
        });
        noonbutton = (ImageButton) findViewById(R.id.noon_button);
        noonbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.decrement(username, day_of_week, 2);
                Toast.makeText(v.getContext(), "Успех!", Toast.LENGTH_LONG).show();

            }
        });
        eveningbutton = (ImageButton) findViewById(R.id.evening_button);
        eveningbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.decrement(username, day_of_week, 3);
                Toast.makeText(v.getContext(), "Успех!", Toast.LENGTH_LONG).show();
            }
        });


    }
    public String formatTextAsIWish(List<List<String>> list) {
        String formatted = new String();
        for(int i = 0; i<list.size(); i++) {
            List<String> row = list.get(i);
            formatted = formatted.concat(row.get(0) + " - " + row.get(1) +"\n");
        }
        return formatted;
    }
}
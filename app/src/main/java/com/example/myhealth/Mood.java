package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Calendar;

public class Mood extends AppCompatActivity {
    String name;
    String username;
    int flag_cough;
    int flag_headache;
    int flag_fever;
    int flag_stuffynose;
    int flag_sorethroat;
    int flag_fatigue;
    int flag_nausea;
    int flag_stomachache;
    int flag_happy = 0;
    int flag_neutral = 0;
    int flag_sad = 0;
    String bloodpressure;
    String bodytemperature;
    EditText bloodPressure;
    EditText bodyTemperature;
    Button moodFinishButton;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        flag_cough = intent.getIntExtra("FLAG_COUGH", 0);
        flag_headache = intent.getIntExtra("FLAG_HEADACHE", 0);
        flag_stuffynose = intent.getIntExtra("FLAG_STUFFYNOSE", 0);
        flag_fever = intent.getIntExtra("FLAG_FEVER", 0);
        flag_sorethroat = intent.getIntExtra("FLAG_SORETHROAT", 0);
        flag_fatigue = intent.getIntExtra("FLAG_FATIGUE", 0);
        flag_nausea = intent.getIntExtra("FLAG_NAUSEA", 0);
        flag_stomachache = intent.getIntExtra("FLAG_STOMACHACHE", 0);

        bloodPressure = (EditText) findViewById(R.id.bloodpressure);
        bodyTemperature = (EditText) findViewById(R.id.bodytemperature);
        moodFinishButton = (Button) findViewById(R.id.moodfinish_button);
        moodFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bloodPressure.getText().toString().trim().length() != 0) {
                    bloodpressure = bloodPressure.getText().toString().trim();
                } else {
                    bloodpressure = "Нема податок!";
                }
                if(bodyTemperature.getText().toString().trim().length() != 0) {
                    bodytemperature = bodyTemperature.getText().toString().trim();
                } else {
                    bodytemperature = "Нема податок!";
                }
                //Внес на сите информации во база
                database = new DBHelper(v.getContext());
                Calendar rightNow = Calendar.getInstance();
                int day = rightNow.get(Calendar.DAY_OF_MONTH);
                int month = rightNow.get(Calendar.MONTH) + 1;
                int year = rightNow.get(Calendar.YEAR);
                String date = day + "/" + month + "/" + year;
                database.insertIntoDailyReports(name, username, date, String.valueOf(flag_cough), String.valueOf(flag_headache), String.valueOf(flag_fever), String.valueOf(flag_stuffynose), String.valueOf(flag_sorethroat), String.valueOf(flag_fatigue), String.valueOf(flag_nausea), String.valueOf(flag_stomachache), String.valueOf(flag_happy), String.valueOf(flag_neutral), String.valueOf(flag_sad), bloodpressure, bodytemperature);
                Intent intent1 = new Intent(v.getContext(), DailyReport.class);
                intent1.putExtra("NAME", name);
                intent1.putExtra("USERNAME", username);
                intent1.putExtra("DATE", date);
                startActivity(intent1);
            }
        });
    }
    public void onMood(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_happy:
                if (checked) {
                    flag_happy = 1;
                }
                break;
            case R.id.checkbox_neutral:
                if (checked) {
                    flag_neutral = 1;
                }
                break;
            case R.id.checkbox_sad:
                if (checked) {
                    flag_sad = 1;
                }
                break;
        }
    }

}
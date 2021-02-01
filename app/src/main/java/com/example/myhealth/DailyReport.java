package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class DailyReport extends AppCompatActivity {
    String name;
    String username;
    String date;
    String flag_cough;
    String flag_headache;
    String flag_fever;
    String flag_stuffynose;
    String flag_sorethroat;
    String flag_fatigue;
    String flag_nausea;
    String flag_stomachache;
    String flag_happy;
    String flag_neutral;
    String flag_sad;
    String bloodpressure;
    String bodytemperature;
    DBHelper database;
    CardView card_cough;
    CardView card_headache;
    CardView card_fever;
    CardView card_stuffynose;
    CardView card_sorethroat;
    CardView card_fatigue;
    CardView card_nausea;
    CardView card_stomachache;
    TextView text_mood;
    TextView text_bloodpressure;
    TextView text_bodytemperature;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_report);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        date = intent.getStringExtra("DATE");
        database = new DBHelper(this);

        List<String> dailyreport = database.getDailyReport(username, date);
        flag_cough = dailyreport.get(0);
        flag_headache = dailyreport.get(1);
        flag_fever= dailyreport.get(2) ;
        flag_stuffynose= dailyreport.get(3);
        flag_sorethroat= dailyreport.get(4);
        flag_fatigue= dailyreport.get(5);
        flag_nausea= dailyreport.get(6);
        flag_stomachache= dailyreport.get(7);
        flag_happy= dailyreport.get(8);
        flag_neutral= dailyreport.get(9);
        flag_sad= dailyreport.get(10);
        bloodpressure= dailyreport.get(11);
        bodytemperature= dailyreport.get(12);

        card_cough = (CardView) findViewById(R.id.card_cough);
        card_headache = (CardView) findViewById(R.id.card_headache);
        card_fever = (CardView) findViewById(R.id.card_fever);
        card_stuffynose = (CardView) findViewById(R.id.card_stuffynose);
        card_sorethroat = (CardView) findViewById(R.id.card_sorethroat);
        card_fatigue = (CardView) findViewById(R.id.card_fatigue);
        card_nausea = (CardView) findViewById(R.id.card_nausea);
        card_stomachache = (CardView) findViewById(R.id.card_stomachache);
        text_mood = (TextView) findViewById(R.id.text_mood);
        text_bloodpressure = (TextView) findViewById(R.id.text_bloodpressure);
        text_bodytemperature = (TextView) findViewById(R.id.text_bodytemperature);
        if(flag_cough.equals("1")) {
            card_cough.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_cough.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_headache.equals("1")) {
            card_headache.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_headache.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_fever.equals("1")) {
            card_fever.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_fever.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_stuffynose.equals("1")) {
            card_stuffynose.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_stuffynose.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_sorethroat.equals("1")) {
            card_sorethroat.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_sorethroat.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_fatigue.equals("1")) {
            card_fatigue.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_fatigue.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_nausea.equals("1")) {
            card_nausea.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_nausea.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_stomachache.equals("1")) {
            card_stomachache.setCardBackgroundColor(Color.parseColor("#C1FF7F"));
        } else {
            card_stomachache.setCardBackgroundColor(Color.WHITE);
        }
        if(flag_happy.equals("1")) {
            text_mood.setText("Расположение: Среќно");
        } else if(flag_neutral.equals("1")) {
            text_mood.setText("Расположение: Неутрално");
        } else if(flag_sad.equals("1")) {
            text_mood.setText("Расположение: Тажно");
        } else {
            text_mood.setText("Расположение: Нема податок!");
        }
        text_bodytemperature.setText("Телесна температура: " + bodytemperature);
        text_bloodpressure.setText("Крвен притисок: " + bloodpressure);
        button = (Button) findViewById(R.id.dailyreport_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), Home.class);
                intent1.putExtra("USERNAME", username);
                intent1.putExtra("NAME", name);
                startActivity(intent1);
            }
        });
    }
}
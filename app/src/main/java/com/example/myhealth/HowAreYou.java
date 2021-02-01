package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class HowAreYou extends AppCompatActivity {
    String name;
    String username;
    int flag_cough = 0;
    int flag_headache = 0;
    int flag_fever = 0;
    int flag_stuffynose = 0;
    int flag_sorethroat = 0;
    int flag_fatigue = 0;
    int flag_nausea = 0;
    int flag_stomachache = 0;
    Button howAreYouContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_are_you);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        howAreYouContinueButton = (Button) findViewById(R.id.howareyou_continue_button);
        howAreYouContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), Mood.class);
                intent1.putExtra("NAME", name);
                intent1.putExtra("USERNAME", username);
                intent1.putExtra("FLAG_COUGH", flag_cough);
                intent1.putExtra("FLAG_HEADACHE", flag_headache);
                intent1.putExtra("FLAG_FEVER", flag_fever);
                intent1.putExtra("FLAG_STUFFYNOSE", flag_stuffynose);
                intent1.putExtra("FLAG_SORETHROAT", flag_sorethroat);
                intent1.putExtra("FLAG_FATIGUE", flag_fatigue);
                intent1.putExtra("FLAG_NAUSEA", flag_nausea);
                intent1.putExtra("FLAG_STOMACHACHE", flag_stomachache);
                startActivity(intent1);
            }
        });
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_cough:
                if (checked) {
                    flag_cough = 1;
                }
                break;
            case R.id.checkbox_headache:
                if (checked) {
                    flag_headache = 1;
                }
                break;
            case R.id.checkbox_fever:
                if (checked) {
                    flag_fever = 1;
                }
                break;
            case R.id.checkbox_stuffynose:
                if (checked) {
                    flag_stuffynose = 1;
                }
                break;
            case R.id.checkbox_sorethroat:
                if (checked) {
                    flag_sorethroat = 1;
                }
                break;
            case R.id.checkbox_fatigue:
                if (checked) {
                    flag_fatigue = 1;
                }
                break;
            case R.id.checkbox_nausea:
                if (checked) {
                    flag_nausea = 1;
                }
                break;
            case R.id.checkbox_stomachache:
                if (checked) {
                    flag_stomachache = 1;
                }
                break;
        }
    }
}
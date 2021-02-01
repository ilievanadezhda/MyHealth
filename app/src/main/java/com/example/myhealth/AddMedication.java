package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedication extends AppCompatActivity {
    String name;
    String username;
    EditText medicationName;
    EditText medicationManufacturer;
    EditText medicationCount;
    EditText medicationDosage;
    Spinner medicationType;
    Button addbutton;
    DBHelper database;

    int flag_monday = 0;
    int flag_tuesday = 0;
    int flag_wednesday = 0;
    int flag_thursday = 0;
    int flag_friday = 0;
    int flag_saturday = 0;
    int flag_sunday = 0;
    int flag_morning = 0;
    int flag_noon = 0;
    int flag_evening = 0;

    String medication_name;
    String medication_manufacturer;
    String medication_count;
    String medication_dosage;
    String medication_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");

        medicationName = (EditText) findViewById(R.id.medication_name);
        medicationManufacturer = (EditText) findViewById(R.id.medication_manufacturer);
        medicationCount = (EditText) findViewById(R.id.medication_count);
        medicationDosage = (EditText) findViewById(R.id.medication_dosage);
        medicationType = (Spinner) findViewById(R.id.medication_type);
        addbutton = (Button) findViewById(R.id.addbutton);
        database = new DBHelper(this);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medication_name = medicationName.getText().toString().trim();
                medication_manufacturer = medicationManufacturer.getText().toString().trim();
                medication_count = medicationCount.getText().toString().trim();
                medication_dosage = medicationDosage.getText().toString().trim();
                medication_type = medicationType.getSelectedItem().toString();
                if(medication_name.length() == 0 || medication_manufacturer.length() == 0 || medication_count.length() == 0 || medication_dosage.length() == 0) {
                    Toast.makeText(v.getContext(), "Ве молиме пополнете ги сите полиња", Toast.LENGTH_LONG).show();

                } else if(database.checkIfMedicationExists(username, medication_name)){
                    Toast.makeText(v.getContext(), "Лекот веќе постои!", Toast.LENGTH_LONG).show();
                } else {
                    Calendar rightNow = Calendar.getInstance();
                    int day = rightNow.get(Calendar.DAY_OF_MONTH);
                    int month = rightNow.get(Calendar.MONTH) + 1;
                    int year = rightNow.get(Calendar.YEAR);
                    String date = day + "/" + month + "/" + year;
                    database.insertIntoMedications(name, username, date, medication_name, medication_type, medication_manufacturer, medication_count, medication_dosage, String.valueOf(flag_morning), String.valueOf(flag_noon), String.valueOf(flag_evening), String.valueOf(flag_monday), String.valueOf(flag_tuesday), String.valueOf(flag_wednesday), String.valueOf(flag_thursday), String.valueOf(flag_friday), String.valueOf(flag_saturday), String.valueOf(flag_sunday));
                    Toast.makeText(v.getContext(), "Успешно додаден лек!", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(v.getContext(), Home.class);
                    intent1.putExtra("NAME", name);
                    intent1.putExtra("USERNAME", username);
                    v.getContext().startActivity(intent1);
                }
            }
        });
    }

    public void onCheckBoxClickedDay(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_monday:
                if (checked) {
                    flag_monday = 1;
                }
                break;
            case R.id.checkbox_tuesday:
                if (checked) {
                    flag_tuesday = 1;
                }
                break;
            case R.id.checkbox_wednesday:
                if (checked) {
                    flag_wednesday = 1;
                }
                break;
            case R.id.checkbox_thursday:
                if (checked) {
                    flag_thursday = 1;
                }
                break;
            case R.id.checkbox_friday:
                if (checked) {
                    flag_friday = 1;
                }
                break;
            case R.id.checkbox_saturday:
                if (checked) {
                    flag_saturday = 1;
                }
                break;
            case R.id.checkbox_sunday:
                if (checked) {
                    flag_sunday = 1;
                }
                break;
        }
    }

    public void onCheckBoxClickedPartOfDay(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox_morning:
                if (checked) {
                    flag_morning = 1;
                }
                break;
            case R.id.checkbox_noon:
                if (checked) {
                    flag_noon = 1;
                }
                break;
            case R.id.checkbox_evening:
                if (checked) {
                    flag_evening = 1;
                }
                break;
        }
    }
}
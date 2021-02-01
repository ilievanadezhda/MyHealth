package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MyMedications extends AppCompatActivity {
    String name;
    String username;
    RecyclerView mRecyclerView;
    MyAdapterMyMedications mAdapter;
    DBHelper database;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medications);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        database = new DBHelper(this);
        List<List<String>> mymedications = database.getMyMedications(username);

        mRecyclerView = (RecyclerView) findViewById(R.id.mymedications_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAdapterMyMedications(mymedications, R.layout.myrow_mymedications, this, name, username, database);
        mRecyclerView.setAdapter(mAdapter);

        button = (Button) findViewById(R.id.mymedications_home);
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
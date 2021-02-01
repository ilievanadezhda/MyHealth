package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.List;

public class OldReports extends AppCompatActivity {
    String name;
    String username;
    RecyclerView mRecyclerView;
    MyAdapterOldReports mAdapter;
    DBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_reports);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");

        database = new DBHelper(this);
        List<String> datesList = database.getDates(username);
        mRecyclerView = (RecyclerView) findViewById(R.id.oldreports_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAdapterOldReports(datesList, R.layout.myrow_oldreports, this, name, username);
        mRecyclerView.setAdapter(mAdapter);
    }
}
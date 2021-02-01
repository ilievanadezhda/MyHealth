package com.example.myhealth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterOldReports extends RecyclerView.Adapter<MyAdapterOldReports.OldReportsViewHolder>{

    private List<String> datesList;
    private int rowLayout;
    private Context mContext;
    private String name;
    private String username;

    public class OldReportsViewHolder extends RecyclerView.ViewHolder {

        public TextView date_oldreport;
        public Button view_oldreport;

        public OldReportsViewHolder(View itemView) {
            super(itemView);
            date_oldreport = (TextView) itemView.findViewById(R.id.date_oldreport);
            view_oldreport = (Button) itemView.findViewById(R.id.view_oldreport);
        }
    }

    public MyAdapterOldReports(List<String> datesList, int rowLayout, Context mContext, String name, String username) {
        this.datesList = datesList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
        this.username = username;
        this.name = name;
    }

    @Override
    public OldReportsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new OldReportsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OldReportsViewHolder viewHolder, int i) {
        final String entry = datesList.get(i);
        viewHolder.date_oldreport.setText(entry);
        viewHolder.view_oldreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DailyReport.class);
                intent.putExtra("NAME", name);
                intent.putExtra("USERNAME", username);
                intent.putExtra("DATE", entry);
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return datesList == null ? 0 : datesList.size();
    }
}

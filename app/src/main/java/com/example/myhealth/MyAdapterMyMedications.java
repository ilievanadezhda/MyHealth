package com.example.myhealth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterMyMedications  extends RecyclerView.Adapter<MyAdapterMyMedications.MyMedicationsViewHolder>{
    private List<List<String>> mymedications;
    private int rowLayout;
    private Context mContext;
    private String name;
    private String username;
    private DBHelper database;

    public class MyMedicationsViewHolder extends RecyclerView.ViewHolder {

        public TextView myrow_mymedications_medication_name;
        public TextView myrow_mymedications_medication_type;
        public TextView myrow_mymedications_remaining;
        public ImageButton delete_button;

        public MyMedicationsViewHolder(View itemView) {
            super(itemView);
            myrow_mymedications_medication_name = (TextView) itemView.findViewById(R.id.myrow_mymedications_medication_name);
            myrow_mymedications_medication_type = (TextView) itemView.findViewById(R.id.myrow_mymedications_medication_type);
            myrow_mymedications_remaining = (TextView) itemView.findViewById(R.id.myrow_mymedications_remaining);
            delete_button = (ImageButton) itemView.findViewById(R.id.delete_button);

        }
    }

    public MyAdapterMyMedications(List<List<String>> mymedications, int rowLayout, Context mContext, String name, String username, DBHelper database) {
        this.mymedications = mymedications;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
        this.username = username;
        this.name = name;
        this.database = database;
    }
    @Override
    public MyMedicationsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new MyMedicationsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyMedicationsViewHolder viewHolder, int i) {
        final List<String> entry = mymedications.get(i);
        viewHolder.myrow_mymedications_medication_name.setText(entry.get(0));
        viewHolder.myrow_mymedications_medication_type.setText(entry.get(1));
        viewHolder.myrow_mymedications_remaining.setText(entry.get(2));
        viewHolder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DBHelper(v.getContext());
                database.deleteMedication(username, entry.get(0));
                Intent intent = new Intent(v.getContext(), MyMedications.class);
                intent.putExtra("NAME", name);
                intent.putExtra("USERNAME", username);
                v.getContext().startActivity(intent);
            }
        });
        }
    @Override
    public int getItemCount() {
        return mymedications == null ? 0 : mymedications.size();
    }
}

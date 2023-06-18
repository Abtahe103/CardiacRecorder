package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AddRecordActivity extends AppCompatActivity {
    Button insert,view;
    EditText date,time,sys,dia,heart,com;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        insert = findViewById(R.id.insertBtn);
        view = findViewById(R.id.viewBtn);
        date = findViewById(R.id.editDate);
        time = findViewById(R.id.editTime);
        sys = findViewById(R.id.editSys);
        dia = findViewById(R.id.editDia);
        heart = findViewById(R.id.editHeart);
        com = findViewById(R.id.editCom);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddRecordActivity.this, RecordListActivity.class));
                finish();
            }
        });
    }

    private void InsertData() {
        String Date = date.getText().toString();
        String Time = time.getText().toString();
        String Sys = sys.getText().toString();
        String Dia = dia.getText().toString();
        String Heart = heart.getText().toString();
        String Com = com.getText().toString();
//        String key = databaseReference.push().getKey();
        Record record = new Record(Date,Time,Sys,Dia,Heart,Com);
        databaseReference.child("User").child(Date).setValue(record);
        Toast.makeText(AddRecordActivity.this, "Result Added", Toast.LENGTH_SHORT).show();

    }
}
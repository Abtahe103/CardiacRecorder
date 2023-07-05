package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class EditRecordActivity extends AppCompatActivity {

    private EditText heart_rate_editText,diastolic_pressure_editText,systolic_pressure_editText,date_editText,time_editText,comment_edittext;
    Button addbutton;
    ArrayList<Record> list;
    DatabaseReference databaseReference;
    RecordAdapter adapter;


    String usrname,key;
    long child_count;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        usrname = HomePage.usrname;

        databaseReference = FirebaseDatabase.getInstance().getReference("Records").child(usrname);
        list = new ArrayList<>();
        adapter = new RecordAdapter(this,list);

        Record record = getIntent().getParcelableExtra("record");

        heart_rate_editText = findViewById(R.id.heart_rate_edit_text);
        diastolic_pressure_editText = findViewById(R.id.diastolic_pressure_edit_text);
        systolic_pressure_editText = findViewById(R.id.systolic_pressure_edit_text);
        date_editText = findViewById(R.id.date_edit_text);
        time_editText = findViewById(R.id.time_edit_text);
        comment_edittext = findViewById(R.id.comment);

        heart_rate_editText.setText(record.getHeart_rt());
        diastolic_pressure_editText.setText(record.getDia_press());
        systolic_pressure_editText.setText(record.getSys_press());
        date_editText.setText(record.getDate());
        time_editText.setText(record.getTime());
        comment_edittext.setText(record.getComment());
        key = record.getKey();

//        adapter.setEditClickListener(new RecordAdapter.EditClickListener() {
//            @Override
//            public void onEditClick(int position) {
//                Record record = list.get(position);
//                heart_rate_editText.setText(record.getHeart_rt());
//                diastolic_pressure_editText.setText(record.getDia_press());
//                systolic_pressure_editText.setText(record.getSys_press());
//                date_editText.setText(record.getDate());
//                time_editText.setText(record.getTime());
//                comment_edittext.setText(record.getComment());
//                key = record.getKey();
//
//
//            }
//        });


//        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Records").child(usrname);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                child_count = snapshot.getChildrenCount();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        addbutton = findViewById(R.id.add_record_btn);

        date_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String enteredDate = date_editText.getText().toString().trim();
                    if (!isValidDate(enteredDate)) {
                        date_editText.setError("Invalid date");
                    }
                }
            }
        });

        time_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    String enteredTime = time_editText.getText().toString().trim();
                    if (!isValidTime(enteredTime)) {
                        time_editText.setError("Invalid time");
                    }
                }
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date_editText.getError()==null){
                    String enteredDate = date_editText.getText().toString().trim();
                    if (!isValidDate(enteredDate)) {
                        date_editText.setError("Invalid date");
                    }
                }
                if(time_editText.getError()==null){
                    String enteredTime = time_editText.getText().toString().trim();
                    if (!isValidTime(enteredTime)) {
                        time_editText.setError("Invalid time");
                    }
                }
                if(heart_rate_editText.getText().length()==0||diastolic_pressure_editText.getText().length()==0||systolic_pressure_editText.getText().length()==0
                        ||date_editText.getText().length()==0||time_editText.getText().length()==0||date_editText.getError()!=null
                        ||time_editText.getError()!=null){
                    if(heart_rate_editText.getText().length()==0){
                        heart_rate_editText.setError("Required Field");
                    }
                    if(diastolic_pressure_editText.getText().length()==0){
                        diastolic_pressure_editText.setError("Required Field");
                    }
                    if(systolic_pressure_editText.getText().length()==0){
                        systolic_pressure_editText.setError("Required Field");
                    }
                    Toast.makeText(getApplicationContext(),"Please Fill out all the required fields in the given format",Toast.LENGTH_SHORT).show();
                }

                else{


                    InsertData();
                }
            }
        });

    }

    private boolean isValidDate(String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        sdf.setLenient(false);
        try {
            Date enteredDate=sdf.parse(date1);
            Date today = new Date();
            return true && enteredDate.before(today);
        } catch (ParseException e) {
            return false;
        }
    }
    private boolean isValidTime(String time) {
        String[] timeParts = time.split(":");
        if (timeParts.length == 2) {
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);

            if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60) {
                return true;
            }
        }
        return false;
    }
    private void InsertData(){
        String date = date_editText.getText().toString();
        String time = time_editText.getText().toString();
        String sys=systolic_pressure_editText.getText().toString();
        String dia = diastolic_pressure_editText.getText().toString();
        String hrt = heart_rate_editText.getText().toString();
        String cmnt = comment_edittext.getText().toString();
//        String key = databaseReference.push().getKey();
        Record record = new Record(key,date,time,sys,dia,hrt,cmnt);



        databaseReference.child(key).setValue(record);
        Toast.makeText(getApplicationContext(),"Record Added",Toast.LENGTH_SHORT).show();

        date_editText.setText("");
        time_editText.setText("");
        systolic_pressure_editText.setText("");
        diastolic_pressure_editText.setText("");
        heart_rate_editText.setText("");
        comment_edittext.setText("");
    }

}
package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddRecordActivity extends AppCompatActivity {
    EditText heart_rate_editText,diastolic_pressure_editText,systolic_pressure_editText,date_editText,time_editText;
    Button addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        heart_rate_editText = findViewById(R.id.heart_rate_edit_text);
        diastolic_pressure_editText = findViewById(R.id.diastolic_pressure_edit_text);
        systolic_pressure_editText = findViewById(R.id.systolic_pressure_edit_text);
        date_editText = findViewById(R.id.date_edit_text);
        time_editText = findViewById(R.id.time_edit_text);

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
                    Toast.makeText(getApplicationContext(),"Record Added",Toast.LENGTH_SHORT).show();
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
}
package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    String usrname;
    CardView add_card,record_card,profile_card,logout_card;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            usrname = bundle.getString("username");
        }

        usrname = getIntent().getStringExtra("username");
        textView = findViewById(R.id.usernametext);
        textView.setText("Hi, How are you feeling today?");


        add_card=findViewById(R.id.add_cardview);
        record_card = findViewById(R.id.records_cardview);
        profile_card = findViewById(R.id.profile_cardview);
        logout_card = findViewById(R.id.logout_cardview);

        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,AddRecordActivity.class);
                intent.putExtra("username",usrname);
                startActivity(intent);
            }
        });

        record_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,RecordListActivity.class);
                intent.putExtra("username",usrname);
                startActivity(intent);
            }
        });

        /**
         * listener for profile_card
         */
        profile_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ProfileViewActivity.class);
                intent.putExtra("username",usrname);
                startActivity(intent);
            }
        });

        logout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
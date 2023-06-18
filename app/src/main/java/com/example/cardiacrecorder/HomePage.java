package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    CardView add_card,record_card,profile_card,logout_card;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        String usrname = getIntent().getStringExtra("username");
        textView = findViewById(R.id.usernametext);
        textView.setText("Hi, "+usrname+"! How are you today?");


        add_card=findViewById(R.id.add_cardview);
        record_card = findViewById(R.id.records_cardview);
        profile_card = findViewById(R.id.profile_cardview);
        logout_card = findViewById(R.id.logout_cardview);

        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, AddRecordActivity.class));
                finish();
            }
        });

        record_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        profile_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
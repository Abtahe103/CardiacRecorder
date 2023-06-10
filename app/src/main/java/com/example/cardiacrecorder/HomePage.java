package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    CardView add_card,record_card,profile_card,logout_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        add_card=findViewById(R.id.add_cardview);
        record_card = findViewById(R.id.records_cardview);
        profile_card = findViewById(R.id.profile_cardview);
        logout_card = findViewById(R.id.logout_cardview);

        add_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            }
        });
    }
}
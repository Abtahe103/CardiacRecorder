package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class ProfileViewActivity extends AppCompatActivity {
    TextView nametext,usernametext,mailtext;
    DatabaseReference databaseReference;
    String usrname,email,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        nametext = findViewById(R.id.name_profile);
        usernametext = findViewById(R.id.username_profile);
        mailtext = findViewById(R.id.mail_profile);

        usrname = HomePage.usrname;
        usernametext.setText("Username : "+usrname);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(usrname);

        databaseReference.child("Email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = snapshot.getValue(String.class);
                mailtext.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.getValue(String.class);
                nametext.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        Toast.makeText(this,"username: "+name+" mail: "+email,Toast.LENGTH_SHORT).show();
    }
}
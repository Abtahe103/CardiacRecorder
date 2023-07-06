package com.example.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * activity for viewing the user profile
 */
public class ProfileViewActivity extends AppCompatActivity {
    TextView nametext,usernametext,mailtext;
    DatabaseReference databaseReference;
    String usrname,email,name;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            usrname = bundle.getString("username");
        }

        mAuth = FirebaseAuth.getInstance();

        String uid = mAuth.getCurrentUser().getUid();

        nametext = findViewById(R.id.name_profile);
//        usernametext = findViewById(R.id.username_profile);
        mailtext = findViewById(R.id.mail_profile);

//        usernametext.setText("Username : "+usrname);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);

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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileViewActivity.this,HomePage.class);
        intent.putExtra("username",usrname);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
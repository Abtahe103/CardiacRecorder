package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class SignupPage extends AppCompatActivity {

    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    int count = 0;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        Button button_signup = findViewById(R.id.btn_signup);
        EditText name = findViewById(R.id.Name);
        EditText usertxt = findViewById(R.id.user_signup);
        EditText emailTxt = findViewById(R.id.email_signup);
        EditText passwordTxt = findViewById(R.id.password_signup);
        progressBar = findViewById(R.id.progressBarId);
        progressBar.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();

            }

            private void userRegister() {
                String fName = name.getText().toString().trim();
                String user = usertxt.getText().toString().trim();
                String email = emailTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();

                if (fName.isEmpty()){
                    name.setError("Enter your first name");
                    name.requestFocus();
                }

                if(user.isEmpty()){
                    usertxt.setError("Enter a username");
                    usertxt.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    emailTxt.setError("Enter an email address");
                    emailTxt.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailTxt.setError("Enter a valid email address");
                    emailTxt.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordTxt.setError("Enter an email address");
                    passwordTxt.requestFocus();
                    return;
                }
                if(password.length()<6){
                    passwordTxt.setError("Minimum length of a password should be 6");
                    passwordTxt.requestFocus();
                    return;
                }

                databaseReference.child("JobSeeker").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(user)){
                            usertxt.setError("User is already registered");
                            usertxt.requestFocus();
                        }
                        else{
                            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
//                                        progressBar.setVisibility(View.VISIBLE);
//                                        Timer timer = new Timer();
//                                        TimerTask timerTask = new TimerTask() {
//                                            @Override
//                                            public void run() {
//                                                count++;
//                                                progressBar.setProgress(count);
//                                                if(count == 30){
//                                                    timer.cancel();

                                                    databaseReference.child("User").child(user).child("Name").setValue(fName);
                                                    databaseReference.child("User").child(user).child("Username").setValue(user);
                                                    databaseReference.child("User").child(user).child("Email").setValue(email);
                                                    databaseReference.child("User").child(user).child("Password").setValue(password);
                                                    Intent intent = new Intent(SignupPage.this,HomePage.class);
                                                    intent.putExtra("username",user);
                                                    startActivity(intent);
//                                                }
//                                            }
//                                        };
//                                        timer.schedule(timerTask,0,30);

                                    }
                                    else{
                                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                            emailTxt.setError("Email is already registered");
                                            emailTxt.requestFocus();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}
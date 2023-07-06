package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * login page activity
 */

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String usrname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button button1 = findViewById(R.id.btn_login);
        Button button2 = findViewById(R.id.btn_signup);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        EditText emailtxt = findViewById(R.id.email_login);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText passwordtxt = findViewById(R.id.password_login);


        mAuth = FirebaseAuth.getInstance();



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }

            private void userLogin() {

                String email = emailtxt.getText().toString().trim();
                String password = passwordtxt.getText().toString().trim();

                if(email.isEmpty()){
                    emailtxt.setError("Enter an email address");
                    emailtxt.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailtxt.setError("Enter a valid email address");
                    emailtxt.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordtxt.setError("Enter an email address");
                    passwordtxt.requestFocus();
                    return;
                }
                if(password.length()<6){
                    passwordtxt.setError("Minimum length of a password should be 6");
                    passwordtxt.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String user = mAuth.getCurrentUser().getUid();
                            Intent intent = new Intent(LoginPage.this, HomePage.class);
                            intent.putExtra("username",user);
                            startActivity(intent);

                    


                        }
                        else {
                            emailtxt.setError("Wrong Email");
                            emailtxt.requestFocus();
                            passwordtxt.setError("Or Wrong Password");
                            passwordtxt.requestFocus();
                        }
                    }
                });

            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,SignupPage.class);
                startActivity(intent);
            }
        });

    }

}
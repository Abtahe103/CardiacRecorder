package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * activity for signup page
 */
public class SignupPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        Button button_signup = findViewById(R.id.btn_signup);
        EditText name = findViewById(R.id.Name);
//        EditText usertxt = findViewById(R.id.user_signup);
        EditText emailTxt = findViewById(R.id.email_signup);
        EditText passwordTxt = findViewById(R.id.password_signup);

        mAuth = FirebaseAuth.getInstance();
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();

            }

            private void userRegister() {
                String fName = name.getText().toString().trim();
//                String user = usertxt.getText().toString().trim();
                String email = emailTxt.getText().toString().trim();
                String password = passwordTxt.getText().toString().trim();

                if (fName.isEmpty()) {
                    name.setError("Enter your first name");
                    name.requestFocus();
                }

//                if (user.isEmpty()) {
//                    usertxt.setError("Enter a username");
//                    usertxt.requestFocus();
//                    return;
//                }
                if (email.isEmpty()) {
                    emailTxt.setError("Enter an email address");
                    emailTxt.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailTxt.setError("Enter a valid email address");
                    emailTxt.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    passwordTxt.setError("Enter an email address");
                    passwordTxt.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    passwordTxt.setError("Minimum length of a password should be 6");
                    passwordTxt.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String user = mAuth.getCurrentUser().getUid();
                            databaseReference.child("User").child(user).child("Name").setValue(fName);
                            databaseReference.child("User").child(user).child("Email").setValue(email);
                            Intent intent = new Intent(SignupPage.this, HomePage.class);

                            intent.putExtra("username", user);
                            startActivity(intent);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                emailTxt.setError("Email is already registered");
                                emailTxt.requestFocus();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
        });

    }
}
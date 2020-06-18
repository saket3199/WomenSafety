package com.example.alpha;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
    private Button signUp;
    TextView TextLogin,TextName;
    public EditText inputEmail;
    public EditText inputpass;
    FirebaseFirestore fstore;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        inputEmail = findViewById(R.id.Signupemail);
        inputpass = findViewById(R.id.signup_pass);
        TextLogin = findViewById(R.id.textlogin);
        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        signUp = findViewById(R.id.Signup);
        TextName = findViewById(R.id.Name);

        TextLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,login.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(signup.this, "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(signup.this, "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //display some message here
                            signup.this.startActivity(new Intent(signup.this, MainActivity.class));

                            signup.this.finish();
                            Toast.makeText(signup.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        } else {
                            //display some message here
                            Toast.makeText(signup.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}
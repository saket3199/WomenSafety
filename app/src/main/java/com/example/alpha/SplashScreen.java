package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashScreen extends AppCompatActivity {
    Handler handler;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //startActivity(new Intent(this,signup.class));

        mAuth = FirebaseAuth.getInstance();
        handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user!= null){
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashScreen.this,login.class));
                    finish();
                }
            }
        },2000);

    }
}

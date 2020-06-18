package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {
    Button signOut,rpass2,a;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        rpass2 = findViewById(R.id.resetpass);
        a = findViewById(R.id.About);

        signOut = findViewById(R.id.Signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(profile.this,login.class));

            }
        });

        rpass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this,forgetpassword.class));
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this,about.class));
            }
        });


    }
}

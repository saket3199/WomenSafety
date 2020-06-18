package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {
    private EditText semail;
    private Button rpass;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        mAuth= FirebaseAuth.getInstance();
        semail = findViewById(R.id.EMAil);
        rpass = findViewById(R.id.Repass);


        rpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = semail.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(forgetpassword.this, "Email send", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
            }
        });


    }
}

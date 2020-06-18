package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText Email, pass;
    private Button login;
    private TextView Forget;
    private FirebaseAuth mAuth;
    TextView TextSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.Email);

        pass = findViewById(R.id.Pass);
        login = findViewById(R.id.Login2);
        TextSignUp = findViewById(R.id.textsignup);
        mAuth= FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    showErrorDialog("Check email pass");
                } else {
                    login_activity();
                }
            }
        });

        Forget = findViewById(R.id.ForgetPassword);
        Forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,forgetpassword.class));
            }
        });

        TextSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private boolean validate() {
        boolean valid = true;
        String email = Email.getText().toString().trim();
        String Pass = pass.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Invalid Email");
            valid = false;
        }
        if (Pass.isEmpty()) {
            pass.setError("Password Cant be Empty");
            valid = false;
        }
        return valid;
    }

    public void login_activity() {
        Log.d("chat", "Login.......");
        String email = Email.getText().toString().trim();
        String Pass = pass.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.d("FlashChat", "Problem signing in: " + task.getException());
                    showErrorDialog("Username Or Password Incorrect");
                } else {
                    finish();
                    Intent login = new Intent(login.this, MainActivity.class);
                    login.this.finish();
                    startActivity(login);
                }
            }
        });
    }
}
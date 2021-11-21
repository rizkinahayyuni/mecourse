package org.aplas.mecourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView to_register;
    private EditText log_email, log_password;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        to_register = (TextView)findViewById(R.id.txt_to_register);
        to_register.setOnClickListener(this);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        log_email = (EditText) findViewById(R.id.log_email);
        log_password = (EditText) findViewById(R.id.log_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_to_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = log_email.getText().toString().trim();
        String password = log_password.getText().toString().trim();

        if(email.isEmpty()) {
            log_email.setError("Email is required");
            log_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            log_email.setError("Please provide valid E-mail!");
            log_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            log_password.setError("Password is required");
            log_password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            log_password.setError("Min Password length should be 6 characters!");
            log_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
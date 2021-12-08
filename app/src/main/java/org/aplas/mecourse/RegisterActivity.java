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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_name,edt_interest, edt_email, edt_password;
    private Button register;
    private TextView to_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_interest = (EditText) findViewById(R.id.edt_phone);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);

        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(this);

        to_login = (TextView) findViewById(R.id.txt_to_login);
        to_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_to_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_register:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String name = edt_name.getText().toString().trim();
        String interest = edt_interest.getText().toString().trim();
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();

        if (name.isEmpty()) {
            edt_name.setError("Name is required");
            edt_name.requestFocus();
            return;
        } else if (interest.isEmpty()) {
            edt_interest.setError("Phone is required");
            edt_interest.requestFocus();
            return;
        } else if (email.isEmpty()) {
            edt_email.setError("E-mail is required");
            edt_email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("Please provide valid E-mail!");
            edt_email.requestFocus();
            return;
        } else if (password.isEmpty()) {
            edt_password.setError("Password is required");
            edt_password.requestFocus();
            return;
        }else if (password.length() < 6) {
            edt_password.setError("Min Password length should be 6 characters!");
            edt_password.requestFocus();
            return;
        } else {
//            mAuth.createUserWithEmailAndPassword(edt_email.getText().toString(),edt_password.getText().toString())
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()){
//                                Toast.makeText(RegisterActivity.this,
//                                        "Register gagal karena "+ task.getException().getMessage(),
//                                        Toast.LENGTH_LONG).show();
//                            }else {
//                                //jika sukses akan menuju ke login activity
//                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
//                            }
//                        }
//                    });

            mAuth.createUserWithEmailAndPassword(edt_email.getText().toString(),edt_password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(name,interest,email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                            Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Failed to register! Try again!!!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
//                        else {
//                            Toast.makeText(RegisterActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
//                        }
                        }
                    });
        }
    }
}
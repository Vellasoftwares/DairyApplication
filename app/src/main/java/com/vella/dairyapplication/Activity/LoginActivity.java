package com.vella.dairyapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.vella.dairyapplication.Models.LoginUser;
import com.vella.dairyapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;
    private TextView btnSignUp;
    private TextView btnForgotPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUserName = findViewById(R.id.edit_emailid);
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.buttonLogin);
        btnSignUp = findViewById(R.id.text_nagation_to_register_activity);
        btnForgotPassword = findViewById(R.id.text_forgotpassword);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValidData()) {
                    LoginUser user = new LoginUser();
                    String email = editUserName.getText().toString().trim();
                    String password = editPassword.getText().toString().trim();
                    user.setUname(email);
                    user.setPassword(password);
                    loginUser(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "wrong credentials!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidData() {
        if (!editUserName.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong Credentials!! please try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}

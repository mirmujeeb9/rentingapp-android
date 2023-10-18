package com.faizanahmed.i200546;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
   private EditText EmailInput;
    private EditText PasswordInput;
    private Button ForgotText ;
    private Button LoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         EmailInput=findViewById(R.id.EmailInput);
         PasswordInput=findViewById(R.id.PasswordInput);
         ForgotText = findViewById(R.id.ForgotText);
         LoginBtn = findViewById(R.id.LoginBtn);
        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.LoginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });



        ForgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });



        Button signUpBtn = findViewById(R.id.SignupBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }


    private void loginUser() {
        String email = EmailInput.getText().toString().trim();
        String password = PasswordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Email and Password are required", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success
                            startActivity(new Intent(MainActivity.this, MainActivity2.class));
                            finish();
                        } else {
                            // If login fails, display a message
                            String errorMessage = task.getException().getMessage(); // Get the detailed error message
                            Toast.makeText(MainActivity.this, "Authentication failed: " + errorMessage, Toast.LENGTH_SHORT).show();                      }
                    }
                });
    }
}

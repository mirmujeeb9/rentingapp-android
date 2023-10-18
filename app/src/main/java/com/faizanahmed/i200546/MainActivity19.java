package com.faizanahmed.i200546;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity19 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!=null){
                    Intent intent=new Intent(MainActivity19.this, MainActivity15.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(MainActivity19.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },500);

                }
            }
        };
        mAuth.addAuthStateListener(authStateListener);



    }
}
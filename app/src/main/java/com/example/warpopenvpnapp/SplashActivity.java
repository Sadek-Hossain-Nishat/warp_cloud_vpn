package com.example.warpopenvpnapp;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Handle the splash screen transition.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);

    }
}
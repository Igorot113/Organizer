package com.igor.organizer.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.igor.organizer.R;

public class SplashActivity extends AppCompatActivity {
    private static final int TEMPO_SPLASH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
            long userId = sharedPref.getLong("user_id", -1);
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        },TEMPO_SPLASH);
    }
}

package com.example.meme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class slashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(slashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            } }, 3000);
    }
    }

package com.example.life_deposit_book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class practice_active_mood extends AppCompatActivity {
    Button mainbutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practiceactivemood);

        mainbutton = findViewById(R.id.button_main2);
        mainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(practice_active_mood.this, MainActivity.class);//之後要改成放教材的頁面

                startActivity(i);
            }
        });
    }
}

package com.example.life_deposit_book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AppEvaluation extends AppCompatActivity {
    Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appevaluation);

        button = findViewById(R.id.buttonMain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"TEST" + position, Toast.LENGTH_SHORT).show();
                //這邊要把問卷結果儲存並傳到資料庫
                Intent i = new Intent(AppEvaluation.this, MainActivity.class);//之後要改成放教材的頁面
                startActivity(i);
            }
        });
    }
}


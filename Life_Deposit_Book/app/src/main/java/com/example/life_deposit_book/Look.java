package com.example.life_deposit_book;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Look extends AppCompatActivity {
    EditText look_first_question, look_second_question;
    TextView question01, question02, start03;
    RadioButton q1, q2, q3, q4, q5;
    RadioButton happy1, happy2, soso1, soso2, sad1, sad2, angry1,angry2;

    String title, text1, text2;
    int option1, option2, option3;
    int s1=0;

    Cursor cursor;
    SQLiteDatabase db = null;
    final static String db_name = "db3.db";
    final static String table_name = "table03";
    final static String create_table = "CREATE TABLE "+table_name+"(_id INTEGER PRIMARY KEY, title TEXT, first_question TEXT, second_question TEXT, typequestion INT, feelings INT, category INT)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        look_first_question = (EditText)findViewById(R.id.looknote_text1);
        look_second_question = (EditText)findViewById(R.id.looknote_text2);

        question01 = (TextView)findViewById(R.id.look_question1);
        question02 = (TextView)findViewById(R.id.look_question2);
        start03 = (TextView)findViewById(R.id.look_start3);

        q1 = (RadioButton) findViewById(R.id.look_radioButton_q1);
        q2 = (RadioButton) findViewById(R.id.look_radioButton_q2);
        q3 = (RadioButton) findViewById(R.id.look_radioButton_q3);
        q4 = (RadioButton) findViewById(R.id.look_radioButton_q4);
        q5 = (RadioButton) findViewById(R.id.look_radioButton_q5);

        happy1 = (RadioButton) findViewById(R.id.look_radioButton_happy1);
        happy2 = (RadioButton) findViewById(R.id.look_radioButton_happy2);
        soso1  = (RadioButton) findViewById(R.id.look_radioButton_soso1);
        soso2  = (RadioButton) findViewById(R.id.look_radioButton_soso2);
        sad1   = (RadioButton) findViewById(R.id.look_radioButton_sad1);
        sad2   = (RadioButton) findViewById(R.id.look_radioButton_sad2);
        angry1 = (RadioButton) findViewById(R.id.look_radioButton_angry1);
        angry2 = (RadioButton) findViewById(R.id.look_radioButton_angry2);

        db = openOrCreateDatabase("db3.db", MODE_PRIVATE, null);
        try {
            db.execSQL(create_table);
        } catch (Exception e) {
            // TODO: handle exception
        }

        Bundle b2 = getIntent().getExtras();
        if(b2 != null){
            s1=b2.getInt("key1");//抓取來自b2的key1


            cursor = db.rawQuery("SELECT * FROM table03 WHERE _id = '" + s1 +"'", null);
            cursor.moveToFirst();

            title = cursor.getString(1);
            text1 = cursor.getString(2);
            text2 = cursor.getString(3);
            option1 = cursor.getInt(4);
            option2 = cursor.getInt(5);
            option3 = cursor.getInt(6);
            System.out.println(option3);

            look_first_question.setText(text1);
            look_first_question.setFocusable(false);
            look_first_question.setFocusableInTouchMode(false);
            look_second_question.setText(text2);
            look_second_question.setFocusable(false);
            look_second_question.setFocusableInTouchMode(false);

            //這邊要還原狀態就是一大串QQ，先從三大類開始。
            if(option3 == 1){
                question01.setText("1.在學習上，我最近遭遇到哪些問題？\n(由於...，所以發生...)\n");
                question02.setText("2.在學習上，我遭遇到問題心情為何？\n(因為...，所以我覺得...)\n");
                start03.setText("是遇到了甚麼學習上的問題呢？");
                q1.setText("學業成績不理想");
                q2.setText("學業成績好");
                q3.setText("學習困難");
                q4.setText("其他學習相關問題");
                q5.setText("其他學習相關問題");
            }
            if(option3 == 2){
                question01.setText("1.在人際溝通上，我最近遭遇到哪些問題？\n(由於...，所以發生...)\n");
                question02.setText("2.在人際溝通上，我遭遇到問題心情如何？\n(因為...，所以我覺得...)\n");
                start03.setText("是遇到了甚麼人際溝通上的問題呢？");
                q1.setText("同儕溝通");
                q2.setText("兩性溝通");
                q3.setText("親人溝通");
                q4.setText("師長溝通");
                q5.setText("其他溝通問題");
            }
            if(option3 == 3){
                question01.setText("1.在未來發展上，我最近思考到哪些問題？\n(由於...，所以發生...)\n");
                question02.setText("2.在未來發展上，我思考問題時的心情為何？\n(因為...，所以我覺得...)\n");
                start03.setText("是遇到了甚麼未來發展上的問題呢？");
                q1.setText("系上專業與就業方向吻合");
                q2.setText("系上專業與就業方向有落差");
                q3.setText("不清楚自己未來職場方向");
                q4.setText("繼續讀研究所的選擇");
                q5.setText("其他未來發展問題");
            }

            if(option1 == 1){
                q1.setChecked(true);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);
                q5.setEnabled(false);
            }
            if(option1 == 2){
                q2.setChecked(true);
                q1.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);
                q5.setEnabled(false);
            }
            if(option1 == 3){
                q3.setChecked(true);
                q1.setEnabled(false);
                q2.setEnabled(false);
                q4.setEnabled(false);
                q5.setEnabled(false);
            }
            if(option1 == 4){
                q4.setChecked(true);
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q5.setEnabled(false);
            }
            if(option1 == 5){
                q5.setChecked(true);
                q1.setEnabled(false);
                q2.setEnabled(false);
                q3.setEnabled(false);
                q4.setEnabled(false);
            }

            if(option2 == 1){
                happy1.setChecked(true);
                happy2.setEnabled(false);
                soso1.setEnabled(false);
                soso2.setEnabled(false);
                sad1.setEnabled(false);
                sad2.setEnabled(false);
                angry1.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 2){
                happy2.setChecked(true);
                happy1.setEnabled(false);
                soso1.setEnabled(false);
                soso2.setEnabled(false);
                sad1.setEnabled(false);
                sad2.setEnabled(false);
                angry1.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 3){
                soso1.setChecked(true);
                happy1.setEnabled(false);
                happy2.setEnabled(false);
                soso2.setEnabled(false);
                sad1.setEnabled(false);
                sad2.setEnabled(false);
                angry1.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 4){
                soso2.setChecked(true);
                happy1.setEnabled(false);
                happy2.setEnabled(false);
                soso1.setEnabled(false);
                sad1.setEnabled(false);
                sad2.setEnabled(false);
                angry1.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 5){
                sad1.setChecked(true);
                happy1.setEnabled(false);
                happy2.setEnabled(false);
                soso1.setEnabled(false);
                soso2.setEnabled(false);
                sad2.setEnabled(false);
                angry1.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 6){
                sad2.setChecked(true);
                happy1.setEnabled(false);
                happy2.setEnabled(false);
                soso1.setEnabled(false);
                soso2.setEnabled(false);
                sad1.setEnabled(false);
                angry1.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 7){
                angry1.setChecked(true);
                happy1.setEnabled(false);
                happy2.setEnabled(false);
                soso1.setEnabled(false);
                soso2.setEnabled(false);
                sad1.setEnabled(false);
                sad2.setEnabled(false);
                angry2.setEnabled(false);
            }
            if(option2 == 8){
                angry2.setChecked(true);
                happy1.setEnabled(false);
                happy2.setEnabled(false);
                soso1.setEnabled(false);
                soso2.setEnabled(false);
                sad1.setEnabled(false);
                sad2.setEnabled(false);
                angry1.setEnabled(false);
            }


            //System.out.println(look_title.getText().toString());
            //System.out.println(look_text.getText().toString());
        }
        else{
            System.out.println("NG...");
        }

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //db.execSQL("DROP TABLE table02");
        db.close();
    }
}

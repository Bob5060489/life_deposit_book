package com.example.life_deposit_book;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class newNote extends AppCompatActivity {
    //原先是TEST，拿來做三大類的選擇，之後視情況要改變全部下面的文字。
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton0, mRadioButton1, mRadioButton2;

    //待改變的項目
    private TextView question01, example01, question02, example02, start03;

    //選完類別之後，會選那個類別的問題。有一個q5之後解決。
    //也是在待改變的項目中。
    private RadioGroup typequestion;
    private RadioButton q1, q2, q3, q4, q5;

    //選表情，這個不受三大項改變而影響。
    private RadioGroup feel;
    private RadioButton happy1, happy2, soso1, soso2, sad1, sad2, angry1,angry2;

    //記事新增、資料庫儲存部分(主要是回答問題)
    private Button newnote_back;
    EditText newnote_title, newnote_answer1, newnote_answer2;

    SQLiteDatabase db = null;
    final static String db_name = "db3.db";
    final static String table_name = "table03";
    final static String create_table = "CREATE TABLE "+table_name+"(_id INTEGER PRIMARY KEY, title TEXT, first_question TEXT, second_question TEXT, typequestion INT, feelings INT, category INT)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnote);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //原先是TEST，拿來做三大類的選擇。
        mRadioButton0 = (RadioButton) findViewById(R.id.mRadioButton0);
        mRadioButton1 = (RadioButton) findViewById(R.id.mRadioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.mRadioButton2);

        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioGroup.check(R.id.mRadioButton0); //設定 mRadioButton0 選項為選取狀態
        mRadioGroup.setOnCheckedChangeListener(radGrpRegionOnCheckedChange); //設定單選選項監聽器

        //選完類別之後，會選那個類別的問題。
        q1 = (RadioButton) findViewById(R.id.radioButton_q1);
        q2 = (RadioButton) findViewById(R.id.radioButton_q2);
        q3 = (RadioButton) findViewById(R.id.radioButton_q3);
        q4 = (RadioButton) findViewById(R.id.radioButton_q4);
        q5 = (RadioButton) findViewById(R.id.radioButton_q5);

        typequestion = (RadioGroup) findViewById(R.id.typequestion);
        //typequestion.check(R.id.radioButton_q1); //設定 mRadioButton0 選項為選取狀態
        typequestion.setOnCheckedChangeListener(typequestion_select); //設定單選選項監聽器

        //選表情，這個不受三大項改變而影響。
        happy1 = (RadioButton) findViewById(R.id.radioButton_happy1);
        happy2 = (RadioButton) findViewById(R.id.radioButton_happy2);
        soso1  = (RadioButton) findViewById(R.id.radioButton_soso1);
        soso2  = (RadioButton) findViewById(R.id.radioButton_soso2);
        sad1   = (RadioButton) findViewById(R.id.radioButton_sad1);
        sad2   = (RadioButton) findViewById(R.id.radioButton_sad2);
        angry1 = (RadioButton) findViewById(R.id.radioButton_angry1);
        angry2 = (RadioButton) findViewById(R.id.radioButton_angry2);

        feel = (RadioGroup) findViewById(R.id.feel);
        //feel.check(R.id.radioButton_happy1); //設定 mRadioButton0 選項為選取狀態
        feel.setOnCheckedChangeListener(feel_select); //設定單選選項監聽器

        //待改變的項目
        question01 = (TextView)findViewById(R.id.question1);
        example01 = (TextView)findViewById(R.id.example1);
        question02 = (TextView)findViewById(R.id.question2);
        example02 = (TextView)findViewById(R.id.example2);
        start03 = (TextView)findViewById(R.id.start3);


        //新增記事跟資料庫儲存部分
        newnote_back = (Button)findViewById(R.id.newnote_back);
        newnote_title= (EditText)findViewById(R.id.newnote_title);
        newnote_answer1 = (EditText)findViewById(R.id.newnote_text1);
        newnote_answer2 = (EditText)findViewById(R.id.newnote_text2);

        db = openOrCreateDatabase("db3.db", MODE_PRIVATE, null);
        try {
            db.execSQL(create_table);
        } catch (Exception e) {
            // TODO: handle exception
        }


        newnote_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String title_default = null;

                if(newnote_title.getText().toString().equals("")){
                    String apply_title = null;

                    if(mRadioButton0.isChecked()){
                        apply_title = " 心情記事　類型：學習問題";
                    }
                    if(mRadioButton1.isChecked()){
                        apply_title = " 心情記事　類型：人際溝通";
                    }
                    if(mRadioButton2.isChecked()) {
                        apply_title = " 心情記事　類型：未來發展";
                    }


                    Calendar calendar = Calendar.getInstance();
                    String now = ""+calendar.get(Calendar.YEAR)
                            +"/"+(calendar.get(Calendar.MONTH)+1)
                            +"/"+calendar.get(Calendar.DATE)
                            +" "+calendar.get(Calendar.HOUR_OF_DAY)
                            +":"+calendar.get(Calendar.MINUTE)
                            + apply_title;

                    newnote_title.setText(now);
                    title_default = now;
                }

                //這兩邊暫定這樣，之後是要做防呆。
                if(newnote_answer1.getText().toString().equals("")){
                    newnote_answer1.setText("no input");
                }

                if(newnote_answer2.getText().toString().equals("")){
                    newnote_answer2.setText("no input");
                }

                //使用數字來儲存單選鈕。
                int tmp1 = 0;
                int tmp2 = 0;
                int tmp3 = 0;

                //這邊是大類下面的五個問題
                if(q1.isChecked()){
                    tmp1 = 1;
                }
                if(q2.isChecked()){
                    tmp1 = 2;
                }
                if(q3.isChecked()){
                    tmp1 = 3;
                }
                if(q4.isChecked()){
                    tmp1 = 4;
                }
                if(q5.isChecked()){
                    tmp1 = 5;
                    System.out.println("5 OK");
                }

                //八個感覺
                if(happy1.isChecked()){
                    tmp2 = 1;
                }
                if(happy2.isChecked()){
                    tmp2 = 2;
                }
                if(soso1.isChecked()){
                    tmp2 = 3;
                }
                if(soso2.isChecked()){
                    tmp2 = 4;
                }
                if(sad1.isChecked()){
                    tmp2 = 5;
                    System.out.println("5 OK");
                }
                if(sad2.isChecked()){
                    tmp2 = 6;
                }
                if(angry1.isChecked()){
                    tmp2 = 7;
                }
                if(angry2.isChecked()){
                    tmp2 = 8;
                }

                //三大類
                if(mRadioButton0.isChecked()){
                    tmp3 = 1;
                }
                if(mRadioButton1.isChecked()){
                    tmp3 = 2;
                }
                if(mRadioButton2.isChecked()){
                    tmp3 = 3;
                    System.out.println("3 OK");
                }


                ContentValues cv = new ContentValues();
                cv.put("title",title_default);
                cv.put("first_question", newnote_answer1.getText().toString());
                cv.put("second_question", newnote_answer2.getText().toString());
                cv.put("typequestion", tmp1);
                System.out.println("5 OK");
                cv.put("feelings", tmp2);
                System.out.println("5 OK");
                cv.put("category", tmp3);
                System.out.println("3 OK");

                db.insert("table03", null, cv);

                Intent i = new Intent(newNote.this,MainActivity.class);
                startActivity(i);

                finish();
            }
        });

    }

    private RadioGroup.OnCheckedChangeListener radGrpRegionOnCheckedChange =
            new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    // TODO Auto-generated method stub
                    //String str = getString(R.string.select_region);

                    switch (checkedId)
                    {
                        case R.id.mRadioButton0: //case mRadioButton0.getId():
                            //System.out.println("1");
                            question01.setText("1.在學習上，我最近遭遇到哪些問題？\n(由於...，所以發生...)\n※參考範例：");
                            example01.setText("由於這學期沉迷於英雄聯盟這個線上遊戲，導致期中考成績不佳，所以發生了期末成績若是沒有提高，就要再重修一次的難題。");
                            question02.setText("2.在學習上，我遭遇到問題心情為何？\n(因為...，所以我覺得...)\n※參考範例：");
                            example02.setText("因為這學期的怠惰，導致成績嚴重下滑，所以我覺得我自己罪有應得。");
                            start03.setText("是遇到了甚麼學習上的問題呢？");
                            q1.setText("學業成績不理想");
                            q2.setText("學業成績好");
                            q3.setText("學習困難");
                            q4.setText("其他學習相關問題");
                            q5.setText("其他學習相關問題");
                            break;
                        case R.id.mRadioButton1: //case mRadioButton1.getId():
                            //System.out.println("2");
                            question01.setText("1.在人際溝通上，我最近遭遇到哪些問題？\n(由於...，所以發生...)\n※參考範例：");
                            example01.setText("由於對方對於時間觀念較為薄弱，所以發生事前約好的事情被迫取消或是延後。");
                            question02.setText("2.在人際溝通上，我遭遇到問題心情如何？\n(因為...，所以我覺得...)\n※參考範例：");
                            example02.setText("因為對方時常遲到，所以我覺得很不愉快，總是要浪費許多時間在等待對方，還會擔心對方會不會因為要趕來而發生意外。");
                            start03.setText("是遇到了甚麼人際溝通上的問題呢？");
                            q1.setText("同儕溝通");
                            q2.setText("兩性溝通");
                            q3.setText("親人溝通");
                            q4.setText("師長溝通");
                            q5.setText("其他溝通問題");
                            break;
                        case R.id.mRadioButton2: //case mRadioButton2.getId():
                            question01.setText("1.在未來發展上，我最近思考到哪些問題？\n(由於...，所以發生...)\n※參考範例：");
                            example01.setText("由於系上所學是當前的新興產業，所以發生企業到學校搶人才的現象。");
                            question02.setText("2.在未來發展上，我思考問題時的心情為何？\n(因為...，所以我覺得...)\n※參考範例：");
                            example02.setText("因為所學的課程是符合課程的需求，所以我覺得自己可以夠深入學習課程領域，學習的面向也可以更加遼闊，面對未來的就業市場，有滿滿的信心跟熱情。");
                            start03.setText("是遇到了甚麼未來發展上的問題呢？");
                            q1.setText("系上專業與就業方向吻合");
                            q2.setText("系上專業與就業方向有落差");
                            q3.setText("不清楚自己未來職場方向");
                            q4.setText("繼續讀研究所的選擇");
                            q5.setText("其他未來發展問題");
                            //System.out.println("3");
                            break;
                    }
                }
            };

    private RadioGroup.OnCheckedChangeListener typequestion_select =
            new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    // TODO Auto-generated method stub
                    //String str = getString(R.string.select_region);

                    switch (checkedId)
                    {
                        case R.id.radioButton_q1: //case mRadioButton0.getId():
                            System.out.println("11");
                            break;
                        case R.id.radioButton_q2: //case mRadioButton1.getId():
                            System.out.println("22");
                            break;
                        case R.id.radioButton_q3: //case mRadioButton2.getId():
                            System.out.println("33");
                            break;
                        case R.id.radioButton_q4: //case mRadioButton2.getId():
                            System.out.println("44");
                            break;
                    }
                }
            };

    private RadioGroup.OnCheckedChangeListener feel_select =
            new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId)
                {
                    // TODO Auto-generated method stub
                    //String str = getString(R.string.select_region);

                    switch (checkedId)
                    {
                        case R.id.radioButton_happy1: //case mRadioButton0.getId():
                            System.out.println("111");
                            break;
                        case R.id.radioButton_happy2: //case mRadioButton1.getId():
                            System.out.println("222");
                            break;
                        case R.id.radioButton_soso1: //case mRadioButton2.getId():
                            System.out.println("333");
                            break;
                        case R.id.radioButton_soso2: //case mRadioButton2.getId():
                            System.out.println("444");
                            break;
                        case R.id.radioButton_sad1: //case mRadioButton2.getId():
                            System.out.println("555");
                            break;
                        case R.id.radioButton_sad2: //case mRadioButton2.getId():
                            System.out.println("666");
                            break;
                        case R.id.radioButton_angry1: //case mRadioButton2.getId():
                            System.out.println("777");
                            break;
                        case R.id.radioButton_angry2: //case mRadioButton2.getId():
                            System.out.println("888");
                            break;
                    }
                }
            };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //db.execSQL("DROP TABLE table03");
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.insert, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){ //�ھکҿ諸Item����
            case R.id.clear:
                newnote_title.setText("");
                newnote_answer1.setText("");
                break;
            case R.id.back:
                Intent i = new Intent(newNote.this,MainActivity.class);
                startActivity(i);

                finish();
                break;
            default:

                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}

package com.example.life_deposit_book;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class afterTest extends AppCompatActivity {
    TextView Questionnaire, group_1, group_2, group_3,
            questionnaire_q1,  questionnaire_q2,  questionnaire_q3,  questionnaire_q4,  questionnaire_q5,  questionnaire_q6,  questionnaire_q7,  questionnaire_q8,  questionnaire_q9,  questionnaire_q10,
            questionnaire_q11, questionnaire_q12, questionnaire_q13, questionnaire_q14, questionnaire_q15, questionnaire_q16, questionnaire_q17, questionnaire_q18, questionnaire_q19, questionnaire_q20,
            questionnaire_q21, questionnaire_q22, questionnaire_q23, questionnaire_q24, questionnaire_q25, questionnaire_q26, questionnaire_q27, questionnaire_q28, questionnaire_q29, questionnaire_q30,
            questionnaire_q31, questionnaire_q32, questionnaire_q33, questionnaire_q34;
    //其中，第一類有34個問題，第二類33，第三類27。
    Button nextPage;

    String title, text1, text2;
    int option1, option2, option3;
    int s1=0;

    Cursor cursor;
    SQLiteDatabase db = null;
    final static String db_name = "db3.db";
    final static String table_name = "table03";
    final static String create_table = "CREATE TABLE "+table_name+"(_id INTEGER PRIMARY KEY, title TEXT, first_question TEXT, second_question TEXT, typequestion INT, feelings INT, category INT)";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aftertest);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Questionnaire = (TextView)findViewById(R.id.questionnaire_title);//問卷標題
        nextPage = (Button)findViewById(R.id.button100);

        group_1 = (TextView)findViewById(R.id.group1);group_2 = (TextView)findViewById(R.id.group2);group_3 = (TextView)findViewById(R.id.group3);

        questionnaire_q1  = (TextView)findViewById(R.id.questionnaire_question1) ;questionnaire_q2  = (TextView)findViewById(R.id.questionnaire_question2) ;questionnaire_q3  = (TextView)findViewById(R.id.questionnaire_question3) ;
        questionnaire_q4  = (TextView)findViewById(R.id.questionnaire_question4) ;questionnaire_q5  = (TextView)findViewById(R.id.questionnaire_question5) ;questionnaire_q6  = (TextView)findViewById(R.id.questionnaire_question6) ;
        questionnaire_q7  = (TextView)findViewById(R.id.questionnaire_question7) ;questionnaire_q8  = (TextView)findViewById(R.id.questionnaire_question8) ;questionnaire_q9  = (TextView)findViewById(R.id.questionnaire_question9) ;
        questionnaire_q10 = (TextView)findViewById(R.id.questionnaire_question10);questionnaire_q11 = (TextView)findViewById(R.id.questionnaire_question11);questionnaire_q12 = (TextView)findViewById(R.id.questionnaire_question12);
        questionnaire_q13 = (TextView)findViewById(R.id.questionnaire_question13);questionnaire_q14 = (TextView)findViewById(R.id.questionnaire_question14);questionnaire_q15 = (TextView)findViewById(R.id.questionnaire_question15);
        questionnaire_q16 = (TextView)findViewById(R.id.questionnaire_question16);questionnaire_q17 = (TextView)findViewById(R.id.questionnaire_question17);questionnaire_q18 = (TextView)findViewById(R.id.questionnaire_question18);
        questionnaire_q19 = (TextView)findViewById(R.id.questionnaire_question19);questionnaire_q20 = (TextView)findViewById(R.id.questionnaire_question20);questionnaire_q21 = (TextView)findViewById(R.id.questionnaire_question21);
        questionnaire_q22 = (TextView)findViewById(R.id.questionnaire_question22);questionnaire_q23 = (TextView)findViewById(R.id.questionnaire_question23);questionnaire_q24 = (TextView)findViewById(R.id.questionnaire_question24);
        questionnaire_q25 = (TextView)findViewById(R.id.questionnaire_question25);questionnaire_q26 = (TextView)findViewById(R.id.questionnaire_question26);questionnaire_q27 = (TextView)findViewById(R.id.questionnaire_question27);
        questionnaire_q28 = (TextView)findViewById(R.id.questionnaire_question28);questionnaire_q29 = (TextView)findViewById(R.id.questionnaire_question29);questionnaire_q30 = (TextView)findViewById(R.id.questionnaire_question30);
        questionnaire_q31 = (TextView)findViewById(R.id.questionnaire_question31);questionnaire_q32 = (TextView)findViewById(R.id.questionnaire_question32);questionnaire_q33 = (TextView)findViewById(R.id.questionnaire_question33);
        questionnaire_q34 = (TextView)findViewById(R.id.questionnaire_question34);

        db = openOrCreateDatabase("db3.db", MODE_PRIVATE, null);
        try {
            db.execSQL(create_table);
        } catch (Exception e) {
            // TODO: handle exception
        }

        Bundle b2 = getIntent().getExtras();
        if(b2 != null) {
            s1 = b2.getInt("key1");//抓取來自b2的key1


            cursor = db.rawQuery("SELECT * FROM table03 WHERE _id = '" + s1 + "'", null);
            cursor.moveToFirst();

            title = cursor.getString(1);
            text1 = cursor.getString(2);
            text2 = cursor.getString(3);
            option1 = cursor.getInt(4);
            option2 = cursor.getInt(5);
            option3 = cursor.getInt(6);//這個是三大類，決定要投怎樣的問卷。

//            System.out.println(title);
//            System.out.println(text1);
//            System.out.println(text2);
//            System.out.println(option1);
//            System.out.println(option2);
//            System.out.println(option3);

            if(option3 == 1){
                Questionnaire.setText("學習問題後測問卷");//這邊之後會很大一坨
                group_1.setText("※情緒覺察");
                group_2.setText("※情緒處理");
                group_3.setText("※情緒因應－學習壓力");
                questionnaire_q1.setText("1. 學習(後).我會因為和別人比較而影響自己的情緒");
                questionnaire_q2.setText("2. 學習(後).我因為別人的閒言閒語而影響自己的情緒");
                questionnaire_q3.setText("3. 學習(後).我對於過去別人傷害我的事情總是耿耿於懷");
                questionnaire_q4.setText("4. 學習(後).我對在意的事情常會產生糾結的情緒困擾");
                questionnaire_q5.setText("5. 學習(後).我會因為自己努力付出卻失敗而覺得沮喪");
                questionnaire_q6.setText("6. 學習(後).我會被過去失敗的陰影所困擾，影響自己繼續努力的勇氣");
                questionnaire_q7.setText("7. 學習(後).事情的發展未達心中的完美目標，令我懊惱");
                questionnaire_q8.setText("8. 學習(後).我能夠清楚地了解自己內心的各種情緒變化");
                questionnaire_q9.setText("9. 學習(後). 我會分析自己情緒變化的原因");
                questionnaire_q10.setText("10. 學習(後).我會以正向的心態面對情緒困擾");
                questionnaire_q11.setText("11. 學習(後).遇到情緒困擾時，我會積極尋找解決方法");
                questionnaire_q12.setText("12. 學習(後).遇到情緒困擾時，我會主動找朋友訴說");
                questionnaire_q13.setText("13. 學習(後).遇到情緒困擾時，我會經由運動來紓解");
                questionnaire_q14.setText("14. 學習(後).遇到情緒困擾時，我會經由吃喝來紓解");
                questionnaire_q15.setText("15. 學習(後).遇到情緒困擾時，我會經由娛樂活動來紓解");
                questionnaire_q16.setText("16. 學習(後).遇到情緒困擾時，我會尋找諮詢管道來紓解");
                questionnaire_q17.setText("17. 學習(後).遇到情緒困擾時，我會經由內心的沉澱和反思來紓解");
                questionnaire_q18.setText("18. 學習(後).學習成績的好壞會影響我的情緒");
                questionnaire_q19.setText("19. 學習(後).我會因為學業的壓力而焦慮");
                questionnaire_q20.setText("20. 學習(後).我會因為和他人比較學習成績而鬱悶");
                questionnaire_q21.setText("21. 學習(後).我知道只要找到方法努力去做，成績自然會變好");
                questionnaire_q22.setText("22. 學習(後).我知道我要規劃讀書計劃，並且實際去執行它");
                questionnaire_q23.setText("23. 學習(後).我知道成績不好並不完全是我很不認真");
                questionnaire_q24.setText("24. 學習(後).我會知道成績不理想只是一個學習的必然過程");
                questionnaire_q25.setText("25. 學習(後).在成績不理想時我能夠為自己打氣");
                questionnaire_q26.setText("26. 學習(後).我能夠找到方法放鬆我的壓力");
                questionnaire_q27.setText("27. 學習(後).我會經由運動來放鬆壓力");
                questionnaire_q28.setText("28. 學習(後).我會經由和同學討論來放鬆壓力");
                questionnaire_q29.setText("29. 學習(後).我會經由娛樂活動來放鬆壓力");
                questionnaire_q30.setText("30. 學習(後).我相信只要努力就一定可以改善學業成績");
                questionnaire_q31.setText("31. 學習(後). 我知道自己必須好好規劃我的讀書計劃");
                questionnaire_q32.setText("32. 學習(後).我會重新擬定學習計劃並努力執行");
                questionnaire_q33.setText("33. 學習(後).我會尋求學習資源，改善學習成績");
                questionnaire_q34.setText("34. 學習(後).我知道我可以找好朋友一起唸書一起進步");
            }
            if(option3 == 2){
                Questionnaire.setText("人際關係後測問卷");
                group_1.setText("※情緒覺察");
                group_2.setText("※情緒處理");
                group_3.setText("※情緒因應－人際壓力");
                questionnaire_q1.setText("1. 學習(後).我會因為和別人比較而影響自己的情緒");
                questionnaire_q2.setText("2. 學習(後).我因為別人的閒言閒語而影響自己的情緒");
                questionnaire_q3.setText("3. 學習(後).我對於過去別人傷害我的事情總是耿耿於懷");
                questionnaire_q4.setText("4. 學習(後).我對在意的事情常會產生糾結的情緒困擾");
                questionnaire_q5.setText("5. 學習(後).我會因為自己努力付出卻失敗而覺得沮喪");
                questionnaire_q6.setText("6. 學習(後).我會被過去失敗的陰影所困擾，影響自己繼續努力的勇氣");
                questionnaire_q7.setText("7. 學習(後).事情的發展未達心中的完美目標，令我懊惱");
                questionnaire_q8.setText("8. 學習(後).我能夠清楚地了解自己內心的各種情緒變化");
                questionnaire_q9.setText("9. 學習(後). 我會分析自己情緒變化的原因");
                questionnaire_q10.setText("10. 學習(後).我會以正向的心態面對情緒困擾");
                questionnaire_q11.setText("11. 學習(後).遇到情緒困擾時，我會積極尋找解決方法");
                questionnaire_q12.setText("12. 學習(後).遇到情緒困擾時，我會主動找朋友訴說");
                questionnaire_q13.setText("13. 學習(後).遇到情緒困擾時，我會經由運動來紓解");
                questionnaire_q14.setText("14. 學習(後).遇到情緒困擾時，我會經由吃喝來紓解");
                questionnaire_q15.setText("15. 學習(後).遇到情緒困擾時，我會經由娛樂活動來紓解");
                questionnaire_q16.setText("16. 學習(後).遇到情緒困擾時，我會尋找諮詢管道來紓解");
                questionnaire_q17.setText("17. 學習(後).遇到情緒困擾時，我會經由內心的沉澱和反思來紓解");
                questionnaire_q18.setText("18. 學習(後).人際互動關係的順利與否會影響我的情緒");
                questionnaire_q19.setText("19. 學習(後).我常會羨慕人際關係好的人");
                questionnaire_q20.setText("20. 學習(後).面對人際互動的問題常會困擾我");
                questionnaire_q21.setText("21. 學習(後).我知道和他人互動狀況不佳，並不完全是我的問題");
                questionnaire_q22.setText("22. 學習(後).我知道和他人溝通不佳，正好是一個學習溝通的契機");
                questionnaire_q23.setText("23. 學習(後).和他人溝通不佳時，我會主動尋找解決方法");
                questionnaire_q24.setText("24. 學習(後).和他人溝通不佳時，我能處理自己的負面情緒");
                questionnaire_q25.setText("25. 學習(後).我會以「真誠的態度」進行溝通，來改善和他人的關係");
                questionnaire_q26.setText("26. 學習(後). 我會運用「同理心」，瞭解他人的感受和心情");
                questionnaire_q27.setText("27. 學習(後).我會經由專注地「傾聽」瞭解他人的觀點和想法");
                questionnaire_q28.setText("28. 學習(後).處理溝通過程的問題時，我能夠抱持「尊重對方的態度」");
                questionnaire_q29.setText("29. 學習(後).我會運用「就事論事」的理性態度和他人進行溝通");
                questionnaire_q30.setText("30. 學習(後).我和人溝通時，會採用「少批評，多讚美」的原則，和同儕進行溝通互動");
                questionnaire_q31.setText("31. 學習(後).我會「適時地運用幽默感」，改善和他人溝通的氣氛");
                questionnaire_q32.setText("32. 學習(後). 我知道我可以找好朋友一起交換經驗及討論如何增強溝通的技巧");
                questionnaire_q33.setText("33. 學習(後).我能夠找到和他人溝通的學習參考教材和資源");
                questionnaire_q34.setText("先放著");
            }
            if(option3 == 3){
                Questionnaire.setText("未來發展後測問卷");
                group_1.setText("※情緒覺察");
                group_2.setText("※情緒處理");
                group_3.setText("※情緒因應－未來發展壓力");
                questionnaire_q1.setText("1. 學習(後).我會因為和別人比較而影響自己的情緒");
                questionnaire_q2.setText("2. 學習(後).我因為別人的閒言閒語而影響自己的情緒");
                questionnaire_q3.setText("3. 學習(後).我對於過去別人傷害我的事情總是耿耿於懷");
                questionnaire_q4.setText("4. 學習(後).我對在意的事情常會產生糾結的情緒困擾");
                questionnaire_q5.setText("5. 學習(後).我會因為自己努力付出卻失敗而覺得沮喪");
                questionnaire_q6.setText("6. 學習(後).我會被過去失敗的陰影所困擾，影響自己繼續努力的勇氣");
                questionnaire_q7.setText("7. 學習(後).事情的發展未達心中的完美目標，令我懊惱");
                questionnaire_q8.setText("8. 學習(後).我能夠清楚地了解自己內心的各種情緒變化");
                questionnaire_q9.setText("9. 學習(後). 我會分析自己情緒變化的原因");
                questionnaire_q10.setText("10. 學習(後).我會以正向的心態面對情緒困擾");
                questionnaire_q11.setText("11. 學習(後).遇到情緒困擾時，我會積極尋找解決方法");
                questionnaire_q12.setText("12. 學習(後).遇到情緒困擾時，我會主動找朋友訴說");
                questionnaire_q13.setText("13. 學習(後).遇到情緒困擾時，我會經由運動來紓解");
                questionnaire_q14.setText("14. 學習(後).遇到情緒困擾時，我會經由吃喝來紓解");
                questionnaire_q15.setText("15. 學習(後).遇到情緒困擾時，我會經由娛樂活動來紓解");
                questionnaire_q16.setText("16. 學習(後).遇到情緒困擾時，我會尋找諮詢管道來紓解");
                questionnaire_q17.setText("17. 學習(後).遇到情緒困擾時，我會經由內心的沉澱和反思來紓解");
                questionnaire_q18.setText("18. 學習(後).對未來生涯發展的不確定性會影響我的情緒");
                questionnaire_q19.setText("19. 學習(後).我會為是否能具備所期望職業的能力而焦慮");
                questionnaire_q20.setText("20. 學習(後). 我會因為就讀學系與所期望職業的差距而煩惱");
                questionnaire_q21.setText("21. 學習(後).我會運用職涯發展網站以找到未來發展的資訊");
                questionnaire_q22.setText("22. 學習(後).我會運用學校生涯諮詢管道獲得協助資源");
                questionnaire_q23.setText("23. 學習(後).我會依據自己期望的未來發展方向做規劃");
                questionnaire_q24.setText("24. 學習(後).我會依據自己規劃的未來發展選修相關課程");
                questionnaire_q25.setText("25. 學習(後).我會找師長或學長姐一起討論未來的發展");
                questionnaire_q26.setText("26. 學習(後).我會找家人一起討論未來的方展方向");
                questionnaire_q27.setText("27. 學習(後).我會採取提升自己未來發展能力的行動");
                questionnaire_q28.setText("先放著");
                questionnaire_q29.setText("先放著");
                questionnaire_q30.setText("先放著");
                questionnaire_q31.setText("先放著");
                questionnaire_q32.setText("先放著");
                questionnaire_q33.setText("先放著");
                questionnaire_q34.setText("先放著");
            }

        }
        else{
            System.out.println("NG...");
        }

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"TEST" + position, Toast.LENGTH_SHORT).show();
                //這邊要把問卷結果儲存並傳到資料庫
                Intent i = new Intent(afterTest.this, AppEvaluation.class);//之後要改成放教材的頁面

                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        db.close();
    }
}

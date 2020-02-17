package com.example.life_deposit_book;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab2 extends Fragment {
    public tab2() {
        // Required empty public constructor
    }

    ListView listView;
    Button beforeTest, praatice, aftertest;

    Cursor cursor;
    SQLiteDatabase db = null;
    final static String db_name = "db3.db";
    final static String table_name = "table03";
    final static String create_table = "CREATE TABLE "+table_name+"(_id INTEGER PRIMARY KEY, title TEXT, first_question TEXT, second_question TEXT, typequestion INT, feelings INT, category INT)";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        listView = (ListView) view.findViewById(R.id.listView2);
        beforeTest = (Button) view.findViewById(R.id.button18);
        praatice  = (Button) view.findViewById(R.id.button20);
        aftertest = (Button) view.findViewById(R.id.button19);

        db = getActivity().openOrCreateDatabase("db3.db", MODE_PRIVATE, null);
        try {
            db.execSQL(create_table);
        } catch (Exception e) {
            // TODO: handle exception
        }
        cursor = getAll();
        UpdataAdapter(cursor);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), Integer.toString(position),Toast.LENGTH_SHORT).show();
            }
        });

        beforeTest.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"TEST" + position, Toast.LENGTH_SHORT).show();
                //新開頁面
                Intent i = new Intent(getActivity(), beforeTest.class);

                int c=cursor.getInt(0);
                Bundle b = new Bundle();
                b.putInt("key1",c );//將ID放入key1
                i.putExtras(b);

                startActivity(i);
            }
        });

        praatice.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), practice_active_mood.class);

                int c=cursor.getInt(0);
                Bundle b = new Bundle();
                b.putInt("key1",c );//將ID放入key1
                i.putExtras(b);

                startActivity(i);
            }
        });

        aftertest.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"TEST" + position, Toast.LENGTH_SHORT).show();
                //新開頁面
                Intent i = new Intent(getActivity(), afterTest.class);

                int c=cursor.getInt(0);
                Bundle b = new Bundle();
                b.putInt("key1",c );//將ID放入key1
                i.putExtras(b);

                startActivity(i);
            }
        });

        return view;
    }

    private void UpdataAdapter(Cursor cursor) {
        // TODO Auto-generated method stub
        if(cursor!=null && cursor.getCount()>=0){
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_multiple_choice, cursor, new String[]{"title"}, new int[] {android.R.id.text1});
            listView.setAdapter(adapter);
        }
    }

    private Cursor getAll() {
        // TODO Auto-generated method stub
        Cursor cursor = db.rawQuery("SELECT * FROM table03",null);

        return cursor;
    }
}

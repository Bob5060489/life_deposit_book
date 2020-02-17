package com.example.life_deposit_book;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
public class note extends Fragment {
    public note() {
        // Required empty public constructor
    }

    Button newButton;
    ListView listView;

    SQLiteDatabase db = null;
    final static String db_name = "db3.db";
    final static String table_name = "table03";
    final static String create_table = "CREATE TABLE "+table_name+"(_id INTEGER PRIMARY KEY, title TEXT, first_question TEXT, second_question TEXT, typequestion INT, feelings INT, category INT)";
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        newButton = (Button) view.findViewById(R.id.button15);
        this.listView = (ListView) view.findViewById(R.id.listView);
        this.registerForContextMenu(listView);

        db = getActivity().openOrCreateDatabase("db3.db", MODE_PRIVATE, null);
        try {
            db.execSQL(create_table);
        } catch (Exception e) {
            // TODO: handle exception
        }
        cursor = getAll();
        UpdataAdapter(cursor);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), newNote.class);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),"TEST",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), Look.class);

                int c=cursor.getInt(0);
                Bundle b = new Bundle();
                b.putInt("key1",c );//將ID放入key1
                i.putExtras(b);

                startActivity(i);
                //System.out.println(c);
                //System.out.println("OK...");

                //getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v == listView){
            menu.setHeaderIcon(R.mipmap.ic_launcher);
            menu.setHeaderTitle("請選擇：");
            menu.add(0, 0, 0, "修改");
            menu.add(0, 1, 0, "刪除");
            menu.add(0, 2, 0, "獲取此item的position");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                db.delete("table03", "_id="+cursor.getInt(0), null);
                cursor = getAll();
                UpdataAdapter(cursor);
                break;
            case 2:
                Toast.makeText(getActivity(), "menuInfo.position:" + menuInfo.position, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void UpdataAdapter(Cursor cursor) {
        // TODO Auto-generated method stub
        if(cursor!=null && cursor.getCount()>=0){
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    getActivity(), android.R.layout.simple_list_item_2, cursor, new String[]{"title","first_question"}, new int[] {android.R.id.text1,android.R.id.text2});
            listView.setAdapter(adapter);
        }
    }

    private Cursor getAll() {
        // TODO Auto-generated method stub
        Cursor cursor = db.rawQuery("SELECT * FROM table03",null);

        return cursor;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //db.execSQL("DROP TABLE table02");
        db.close();
    }
}

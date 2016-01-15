package com.example.kestrella.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    public static DatabaseAdapter dbAdapter;
    public static ArrayAdapter<Data> dataAdapter;
    ArrayList<Integer> entryID = new ArrayList<Integer>();
    ArrayList<String> entryNames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        dataAdapter = new ArrayAdapter<Data>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                dbAdapter.getAllData());

        try {
            db = DatabaseAdapter.database;
            initializeEntries();
            addFriends();

            List<HashMap<String, String>> listOfFriends = new ArrayList<HashMap<String, String>>();
            listOfFriends.clear();

            for (int i = 0; i < entryNames.size(); i++) {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("name", entryNames.get(i));
                listOfFriends.add(hm);
            }
            TextView txtIfNone = (TextView)findViewById(R.id.txtIfNone);
            if(listOfFriends.isEmpty()){
                txtIfNone.setText(R.string.txtVNoFriends);
            }else{
                render(listOfFriends);
            }

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void initializeEntries() {
        Cursor entryQuery = db.rawQuery("SELECT * FROM tblAshley", null);
        if (entryQuery.getCount() != 0) {
            while (entryQuery.moveToNext()) {// go to first row
                entryID.add(entryQuery.getInt(0)); //column 0 w/c is the id
                entryNames.add(entryQuery.getString(1));
            }
        }
        entryQuery.close();
    }

    public void addFriends(){
        Button btnAddFriend = (Button)findViewById(R.id.btnAddFriend);
        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this, Add.class);
                startActivity(add);
                finish();
            }
        });
    }

    public void render(List<HashMap<String, String>> listOfFriends){
        String[] from = {"name"};
        int[] to = {  R.id.entryName };
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), listOfFriends,
                R.layout.list_view, from, to);
        ListView lViewFriends = (ListView) findViewById(R.id.lViewFriends);
        lViewFriends.setAdapter(adapter);
//        registerForContextMenu(mylist);
    }

}

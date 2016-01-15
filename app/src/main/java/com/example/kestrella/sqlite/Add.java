package com.example.kestrella.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eTxtName = (EditText)findViewById(R.id.eTxtName);
                if(eTxtName.getText().toString().trim().isEmpty()){
                    Toast.makeText(Add.this, "Please enter a name!", Toast.LENGTH_SHORT).show();
                }else{
                    int id = MainActivity.dbAdapter.getLastId() + 1;
                    Data newData = new Data(id, eTxtName.getText().toString().trim(), 1);
                    // add data
                    MainActivity.dbAdapter.addAccount(newData);
                    // Add data to array adapter
                    MainActivity.dataAdapter.add(newData);
                    // refresh array adapter
                    MainActivity.dataAdapter.notifyDataSetChanged();
                    Toast.makeText(Add.this, String.format("%s Successfully added!",eTxtName.getText().toString().trim()), Toast.LENGTH_LONG).show();
                    Intent main = new Intent(Add.this, MainActivity.class);
                    startActivity(main);
                    finish();

                }
            }
        });
    }
}

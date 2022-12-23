package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        EditText fname,lname,email;
        Button btn,btn1;@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
         btn = findViewById(R.id.submitbtn);
         btn1=findViewById(R.id.showbtn);

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity .this);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
            String fnamestr = fname.getText().toString();
            String lnamestr = lname.getText().toString();
            String emailstr = email.getText().toString();
            boolean res = databaseHelper.addRecord(fnamestr,lnamestr,emailstr);
             if(res){
                 Toast.makeText(MainActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
             }
             else{
                 Toast.makeText(MainActivity.this, "Record not Inserted", Toast.LENGTH_SHORT).show();
             }
             }
         });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Dblist.class);
                startActivity(i);
            }
        });
        }
    }



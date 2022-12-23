package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class Dblist extends AppCompatActivity {
    RecyclerView dblistview;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblist);
        dblistview = findViewById(R.id.dblistview);
        databaseHelper = new DatabaseHelper(Dblist. this);
        Cursor alldata = databaseHelper.fetchdata();
        ArrayList<user> arrayList= new ArrayList<user>();
        while (alldata.moveToNext()){
            @SuppressLint("Range") int id = alldata.getInt(alldata.getColumnIndex( "id"));
            @SuppressLint("Range") String fname = alldata.getString(alldata.getColumnIndex( "firstname"));
            @SuppressLint("Range") String lname = alldata.getString(alldata.getColumnIndex( "lastname"));
            @SuppressLint("Range") String email = alldata.getString(alldata.getColumnIndex( "email"));

            arrayList.add(new user(id, fname,lname,email));
        }
        my_adapter myAdapter=new my_adapter(arrayList,Dblist.this);
        dblistview.setHasFixedSize(true);
        dblistview.setLayoutManager(new LinearLayoutManager(this));
        dblistview.setAdapter(myAdapter);


    }

    public  void  delete(int id,int position){
        databaseHelper.deleteRecord(id);
    }

}
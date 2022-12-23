package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{
    SQLiteDatabase db;
    public String TABLE_NAME = "user";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "demo.db", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table user(id integer primary key autoincrement,firstname TEXT,lastname TEXT, email TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("Drop table user");
            onCreate(sqLiteDatabase);
    }
    public boolean addRecord(String fname,String lname, String email){
        ContentValues contentvalues = new ContentValues();
    contentvalues.put("firstname",fname);
        contentvalues.put("lastname",lname);
        contentvalues.put("emaiL",email);
        long res = db.insert(TABLE_NAME,null,contentvalues);
        if(res == -1){
            return false;

        }
        else{
            return true;
        }
    }
    public Cursor fetchdata(){
        SQLiteDatabase db = getReadableDatabase();
       Cursor alldata = db.rawQuery("Select * from user", null);
       return alldata;
    }
    public boolean updateRecord(String fname,String lname, String email,int id){
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("firstname",fname);
        contentvalues.put("lastname",lname);
        contentvalues.put("emaiL",email);
        long res = db.update(TABLE_NAME,contentvalues," id=?",new String[]{String.valueOf(id)});
        if(res == -1){
            return false;

        }
        else{
            return true;
        }
    }
    public  boolean deleteRecord(int id){
        SQLiteDatabase db = getWritableDatabase();
        long res = db.delete(TABLE_NAME , "id = ?",new String[]{String.valueOf(id)});
        if(res == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean login(String username , String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor data = db.rawQuery("select * from user where username = ? AND password = ? ",new String[]{String.valueOf(username),String.valueOf(password)});
        if(data != null){
            return  true;
        }
        else{
            return false;
        }

    }


}


package com.example.blooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";

    public DBhelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table users(username varchar, password varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop table if exists users");
    }

    public boolean insert(String username, String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", pass);
        long result=db.insert("users", null, contentValues);
        if(result==-1)
            return false;
        else return true;
    }

    public boolean check(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where username=?", new String[] {username});
        if(c.getCount()>0)
            return true;
        else return false;
    }

    public boolean checkboth(String username, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password=?", new String[] {username, password});
        if(c.getCount()>0)
            return true;
        else return false;
    }

}

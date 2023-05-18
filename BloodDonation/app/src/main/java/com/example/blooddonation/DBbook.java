package com.example.blooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBbook extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DBNAME="users.db";
    private static final String bookings="bookings";
    private static final String dononame="dononame";
    private static final String id="id";
    private static final String bloodtype="bloodtype";
    private static final String donodate="donodate";
    private static final String email="email";

    public DBbook(Context context){ super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bookings(dononame varchar, id varchar, bloodtype varchar, donodate varchar, email varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop table if exists bookings");
    }

    public boolean insert(String dononame, String id, String bloodtype, String donodate, String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("dononame", dononame);
        contentValues.put("id", id);
        contentValues.put("bloodtype", bloodtype);
        contentValues.put("donodate", donodate);
        contentValues.put("email", email);
        long resultset=db.insert("bookings", null, contentValues);
        if(resultset==-1)
            return false;
        else return true;
    }

    public ArrayList<HashMap<String, String>> viewbookings(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<HashMap<String, String>> AL=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT dononame, id, donodate, email FROM bookings",null);
        while (c.moveToNext()){
            HashMap<String,String> b=new HashMap<>();
            b.put("dononame", c.getString(c.getColumnIndexOrThrow(dononame)));
            b.put("id", c.getString(c.getColumnIndexOrThrow(id)));
            b.put("donodate", c.getString(c.getColumnIndexOrThrow(donodate)));
            b.put("email", c.getString(c.getColumnIndexOrThrow(email)));
            AL.add(b);
        }
        return AL;
    }

    public Integer deletebooking(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(bookings, "id=?", new String[]{id});
    }

}

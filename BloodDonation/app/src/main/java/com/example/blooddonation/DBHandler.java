package com.example.blooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DBNAME="usersdb.db";
    private static final String bloodrecords="bloodrecords";
    private static final String id="id";
    private static final String dononame="dononame";
    private static final String recname="recname";
    private static final String bloodtype="bloodtype";
    private static final String donodate="donodate";
    private static final String recdate="recdate";

    public DBHandler(Context context){ super(context, "usersdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bloodrecords(dononame varchar, recname varchar, id varchar, bloodtype varchar, donodate varchar, recdate varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop table if exists bloodrecords");
    }

    public boolean insert(String dononame, String recname, String id, String bloodtype, String donodate, String recdate){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("dononame", dononame);
        contentValues.put("recname", recname);
        contentValues.put("id", id);
        contentValues.put("bloodtype", bloodtype);
        contentValues.put("donodate", donodate);
        contentValues.put("recdate", recdate);
        long resultset=db.insert("bloodrecords", null, contentValues);
        if(resultset==-1)
            return false;
        else return true;
    }

    public ArrayList<HashMap<String, String>> getdetails(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<HashMap<String, String>> AL=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT dononame, id, bloodtype, donodate FROM bloodrecords",null);
        while (c.moveToNext()){
            HashMap<String,String> br=new HashMap<>();
            br.put("dononame", c.getString(c.getColumnIndexOrThrow(dononame)));
            br.put("id",c.getString(c.getColumnIndexOrThrow(id)));
            br.put("bloodtype",c.getString(c.getColumnIndexOrThrow(bloodtype)));
            br.put("donodate",c.getString(c.getColumnIndexOrThrow(donodate)));
            AL.add(br);
        }
        return AL;
    }

        //remove getcodebyid if any error
    public ArrayList<HashMap<String, String>> getrecordbyid(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<HashMap<String, String>> AL=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT dononame, id, bloodtype FROM bloodrecords where id=?", new String[]{id});
        while (c.moveToNext()){
            HashMap<String,String> br=new HashMap<>();
            br.put("dononame",c.getString(c.getColumnIndexOrThrow(dononame)));
            br.put("id", c.getString(c.getColumnIndexOrThrow(id)));
            br.put("bloodtype",c.getString(c.getColumnIndexOrThrow(bloodtype)));
            AL.add(br);
        }
        return AL;
    }

    public Integer deletedetails(String id){
            SQLiteDatabase db=this.getWritableDatabase();
            return db.delete(bloodrecords, "id=?", new String[]{id});
        }

        public boolean updaterecords(String dononame, String recname, String id, String bloodtype, String donodate, String recdate){
        SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("dononame", dononame);
            contentValues.put("recname", recname);
            contentValues.put("id", id);
            contentValues.put("bloodtype", bloodtype);
            contentValues.put("donodate", donodate);
            contentValues.put("recdate", recdate);
            db.update(bloodrecords, contentValues, "ID=?",new String[] { id });
        return true;
        }

}

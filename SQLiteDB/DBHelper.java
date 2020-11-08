package com.example.part3_practice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABAST_VERSION = 3;
    public DBHelper(Context context){
        super(context,"memodb",null,DATABAST_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "create table tb_memo (" +
                "_id integer primary key autoincrement, "
                + "name not null, "
                + "phone, "
                + "email)";
        db.execSQL(memoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABAST_VERSION){
            db.execSQL("drop table tb_memo");
            onCreate(db);
        }
    }
}

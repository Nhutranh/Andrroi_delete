package com.phanngocnhutranh.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "giangvien.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "DSgiangvien";
    public static final String COL_CODE = "Magiangvien";
    public static final String COL_NAME = "Tengiangvien";
    public static final String COL_CLASS = "Lopgiangvien";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE IF NOT EXISTS " + TBL_NAME + " ( " + COL_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(50), " + COL_CLASS + " VARCHAR(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    //select
    public Cursor queryData(String sql){
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery(sql, null);
        }catch (Exception exp){
            return cursor;
        }
    }
    // insert
    public boolean execsql(String sql){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            return true;
        }catch (Exception exp){
            return false;
        }
    }

    public void createData(){
        try {
            execsql(" INSERT INTO " + TBL_NAME + " VALUES(null, 'phan thanh hy', 'mau giao')");
            execsql(" INSERT INTO " + TBL_NAME + " VALUES(null, 'phan thanh hy', 'mau giao 1')");
            execsql(" INSERT INTO " + TBL_NAME + " VALUES(null, 'phan thanh hy', 'mau giao 2')");

        }catch (Exception e){
            Log.e("ERR: ", e.getMessage().toString());
        }
    }

}

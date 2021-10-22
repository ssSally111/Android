package com.example.tallybook;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashSet;
import java.util.Set;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context, "recordiCetea.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table record(id integer primary key autoincrement,type varchar(10),content float,detail varchar(20),date varchar(15),_key varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long insert(Record.Type type, String content, String detail, String date, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type.equals(Record.Type.income) ? "income" : "spending");
        values.put("content", content);
        values.put("detail", detail);
        values.put("date", date);
        values.put("_key", key);
        long id = db.insert("record", null, values);
        db.close();
        return id;
    }

    public void delete(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("record", "_key=?", new String[]{key});
        db.close();
    }

    @SuppressLint("Range")
    public Set<String[]> queryAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<String[]> queryDate = new HashSet<>();
        Cursor cursor = db.query("record", new String[]{"type", "content", "detail", "date", "_key"}, null, null, null, null, null);
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String type = cursor.getString(cursor.getColumnIndex("type"));
                double content = cursor.getFloat(cursor.getColumnIndex("content"));
                String detail = cursor.getString(cursor.getColumnIndex("detail"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String key = cursor.getString(cursor.getColumnIndex("_key"));
                queryDate.add(new String[]{type, String.valueOf(content), detail, date, key});
            }
        } else {
            cursor.close();
            db.close();
            return null;
        }
        cursor.close();
        db.close();
        return queryDate;
    }
}

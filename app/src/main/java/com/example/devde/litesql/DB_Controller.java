package com.example.devde.litesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;



public class DB_Controller extends SQLiteOpenHelper {

    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENTS( ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT UNIQUE);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(db);
    }
    public void insert_student(String firstname,String lastname)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }
    public void delete_student(String firstname)
    {
        this.getWritableDatabase().delete("STUDENTS","FIRSTNAME= '"+firstname+"'",null);
    }
    public void update_student(String old_firstname,String new_firstname)
    {
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_firstname+"' WHERE FIRSTNAME='"+old_firstname+"'");
    }
    public void list_all_students(TextView textView)
    {
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM STUDENTS" , null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1) +" "+ cursor.getString(2)+ "\n");
        }
    }
}

package com.mvltsevinc.mydatabaseapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR,age INT(2))");
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('James',50)");
            //myDatabase.execSQL("INSERT INTO musicians(name,age) VALUES ('Rob',60)");

            //myDatabase.execSQL("DELETE FROM musicians where name='Rob'");
            //myDatabase.execSQL("UPDATE musicians SET age=40 WHERE name='James'");

            Cursor cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE age>59",null);
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            while(cursor != null){
                System.out.println("Name:" + cursor.getString(nameIx));
                System.out.println("Age:"+ cursor.getString(ageIx));
                cursor.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

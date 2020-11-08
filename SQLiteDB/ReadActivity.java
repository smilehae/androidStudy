package com.example.part3_practice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLInput;

import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity);

        TextView nameView = findViewById(R.id.name_text);
        TextView phoneView = findViewById(R.id.phone_text);
        TextView emailView = findViewById(R.id.email_text);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name, phone, email from tb_memo order by _id desc limit 1", null);
        while(cursor.moveToNext()){
            nameView.setText(cursor.getString(0));
            phoneView.setText(cursor.getString(1));
            emailView.setText(cursor.getString(2));
        }
        db.close();

    }
}

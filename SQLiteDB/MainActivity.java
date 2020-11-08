package com.example.part3_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

Button addBtn;
EditText nameEdit;
EditText phoneEdit;
EditText emailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.add_button);
        nameEdit = findViewById(R.id.name_edit);
        phoneEdit = findViewById(R.id.phone_edit);
        emailEdit = findViewById(R.id.email_edit);

        addBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {


        String name = nameEdit.getText().toString();
        String phone = phoneEdit.getText().toString();
        String email = emailEdit.getText().toString();

        if(name.matches("")){

            sendToast("이름이 동록되지 않았습니다.");
        }
        else {
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("insert into tb_memo (name, phone, email) values (?,?,?)", new String[]{name, phone, email});
            db.close();

            Intent intent = new Intent(this, ReadActivity.class);
            startActivity(intent);
        }

    }

    void sendToast(String message){
        Toast t = Toast.makeText(this,message,Toast.LENGTH_SHORT);
        t.show();
    }
}

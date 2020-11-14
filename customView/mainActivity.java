package com.example.part4_13;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements OnMyChangeListner {

    View barView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyPlusMinusView plusMinusView = findViewById(R.id.customView);
        barView = findViewById(R.id.barView);

        plusMinusView.setOnMyChangeListner(this);
    }

    @Override
    public void onChange(int value) {
        if(value<0){
            barView.setBackgroundColor(Color.RED);
        } else if(value < 30){
            barView.setBackgroundColor(Color.YELLOW);
        } else if(value<60){
            barView.setBackgroundColor(Color.BLUE);
        } else {
            barView.setBackgroundColor(Color.GREEN);
        }

    }
}

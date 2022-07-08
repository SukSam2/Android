package com.example.android_textview;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        TextView objTV = new TextView(this);    // TextView class를 가지고 객체선언
        objTV.setText("Android Programming !");

        objTV.setGravity(0x11);         // center's constant value 0x11
        objTV.setTextColor(Color.BLUE); // import android.graphics.Color
        objTV.setTextSize(0x20);        // 32 -> 16진수로 0x20
        setContentView(objTV);

    }
}
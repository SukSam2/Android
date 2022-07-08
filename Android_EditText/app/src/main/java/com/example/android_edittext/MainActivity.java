package com.example.android_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText objET = (EditText) findViewById(R.id.editText);

        objET.setText("Google : ");
        objET.setGravity(0x01);

        // + Android Programming 을 추가로 함께 출력: getText() 사용
//        String strData = objET.getText().toString();
//        strData += "Android Programming";
//        objET.setText(strData);

        // 위의 세 줄을 한 줄로 Optimize
        objET.setText(objET.getText().toString() + "Android Programming");




    }
}
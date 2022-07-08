package com.flyai.android_intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCow, btnDog;
    private Toast objToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCow = (Button) findViewById(R.id.btnCow);
        btnCow.setOnClickListener(this);

        btnDog = (Button) findViewById(R.id.btnDog);
        btnDog.setOnClickListener(this);
    }

    public void onClick(View v){
        if (v == btnCow){
            Intent cowIntent = new Intent(MainActivity.this, CowActivity.class);
//            startActivity(cowIntent);
            startActivityForResult(cowIntent, 1);     // 왜 줄 가는지...?
        } else if (v == btnDog){
            Intent dogIntent = new Intent(MainActivity.this, DogActivity.class);
//            startActivity(dogIntent);
            startActivityForResult(dogIntent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            String strData = data.getStringExtra("Animal_Sound");
            objToast = Toast.makeText(this, strData, Toast.LENGTH_LONG);
            objToast.show();
        }
    }

}
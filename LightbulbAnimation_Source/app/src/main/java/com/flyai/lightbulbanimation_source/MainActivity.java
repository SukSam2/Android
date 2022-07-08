package com.flyai.lightbulbanimation_source;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager objSMG;
    Sensor sensor_Light;
    //    ImageView imageView;
    ImageView objIMG;
    TextView objTXT;
    Vibrator objVIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Light = objSMG.getDefaultSensor(Sensor.TYPE_LIGHT);

        objTXT = (TextView) findViewById(R.id.textView);
        objIMG = (ImageView) findViewById(R.id.imageView);
        objVIB = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public void onResume(){
        super.onResume();
        //Register Listener for changing sensor value
        objSMG.registerListener(this, sensor_Light, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this); // Release sensor Listener
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        objTXT.setText(("Light: " + sensorEvent.values[0] + "(lux)"));
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            if(sensorEvent.values[0] <= 80){
                Animation objANI = AnimationUtils.loadAnimation(this, R.anim.jumping);
                objIMG.startAnimation(objANI);
                objVIB.vibrate(1000);
                objIMG.setImageResource(R.drawable.lighton);
            }else {
                objIMG.setImageResource(R.drawable.lightoff);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }

}
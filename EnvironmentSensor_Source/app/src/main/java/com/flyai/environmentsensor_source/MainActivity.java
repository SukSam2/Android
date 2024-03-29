package com.flyai.environmentsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager objSMG;
    Sensor sensor_AmbientPressure;

    TextView objTV_AmbienPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_AmbientPressure = objSMG.getDefaultSensor(Sensor.TYPE_PRESSURE);

        objTV_AmbienPressure = (TextView) findViewById(R.id.txtAmbientPressure);

    }

    @Override
    public void onResume(){
        super.onResume();
        objSMG.registerListener(this, sensor_AmbientPressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_PRESSURE:
                objTV_AmbienPressure.setText(("Ambient Pressure: " + sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }

}
package com.flyai.motionsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager objSMG;           // Object SensorManager

    Sensor sensor_Gravity;          // Object Sensor Gravity
    Sensor sensor_Accelerometer;
    Sensor sensor_LinearAcceleration;
    Sensor sensor_Gyroscpe;

    TextView objTV_X_Gravity;
    TextView objTV_Y_Gravity;
    TextView objTV_Z_Gravity;

    TextView objTV_X_Accelerometer;
    TextView objTV_Y_Accelerometer;
    TextView objTV_Z_Accelerometer;

    TextView objTV_X_LinearAcceleration;
    TextView objTV_Y_LinearAcceleration;
    TextView objTV_Z_LinearAcceleration;

    TextView objTV_X_Gyroscpe;
    TextView objTV_Y_Gyroscpe;
    TextView objTV_Z_Gyroscpe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Object for access sensor device
        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Gravity = objSMG.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor_Accelerometer = objSMG.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_LinearAcceleration = objSMG.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensor_Gyroscpe = objSMG.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        objTV_X_Gravity = (TextView) findViewById(R.id.txtX_Gravity);
        objTV_Y_Gravity = (TextView) findViewById(R.id.txtY_Gravity);
        objTV_Z_Gravity = (TextView) findViewById(R.id.txtZ_Gravity);

        objTV_X_Accelerometer = (TextView) findViewById(R.id.txtX_Accelerometer);
        objTV_Y_Accelerometer = (TextView) findViewById(R.id.txtY_Accelerometer);
        objTV_Z_Accelerometer = (TextView) findViewById(R.id.txtZ_Accelerometer);

        objTV_X_LinearAcceleration = (TextView) findViewById(R.id.txtX_LinearAcceleration);
        objTV_Y_LinearAcceleration = (TextView) findViewById(R.id.txtY_LinearAcceleration);
        objTV_Z_LinearAcceleration = (TextView) findViewById(R.id.txtZ_LinearAcceleration);

        objTV_X_Gyroscpe = (TextView) findViewById(R.id.txtX_Gyroscpe);
        objTV_Y_Gyroscpe = (TextView) findViewById(R.id.txtY_Gyroscpe);
        objTV_Z_Gyroscpe = (TextView) findViewById(R.id.txtZ_Gyroscpe);

    }

    @Override
    public void onResume(){
        super.onResume();

        //Register Listener for changing sensor value
        objSMG.registerListener(this, sensor_Gravity, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_LinearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Gyroscpe, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this); // Release sensor Listener
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        switch(sensorEvent.sensor.getType()){
            case Sensor.TYPE_GRAVITY:
                objTV_X_Gravity.setText(("X: " + sensorEvent.values[0]));
                objTV_Y_Gravity.setText(("Y: " + sensorEvent.values[1]));
                objTV_Z_Gravity.setText(("Z: " + sensorEvent.values[2]));
                break;
            case Sensor.TYPE_ACCELEROMETER:
                objTV_X_Accelerometer.setText(("X: " + sensorEvent.values[0]));
                objTV_Y_Accelerometer.setText(("Y: " + sensorEvent.values[1]));
                objTV_Z_Accelerometer.setText(("Z: " + sensorEvent.values[2]));
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                objTV_X_LinearAcceleration.setText(("X: " + sensorEvent.values[0]));
                objTV_Y_LinearAcceleration.setText(("Y: " + sensorEvent.values[1]));
                objTV_Z_LinearAcceleration.setText(("Z: " + sensorEvent.values[2]));
                break;
            case Sensor.TYPE_GYROSCOPE:
                objTV_X_Gyroscpe.setText(("X: " + sensorEvent.values[0]));
                objTV_Y_Gyroscpe.setText(("Y: " + sensorEvent.values[1]));
                objTV_Z_Gyroscpe.setText(("Z: " + sensorEvent.values[2]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){
        // Call when sensor accuracy changed
    }
}
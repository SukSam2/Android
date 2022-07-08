package com.flyai.flashlightsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager objSMG;
    Sensor sensor_Illuminance;
//    Sensor sensor_Light;

    Camera objCAM;
    android.hardware.Camera.Parameters objCamPara;

    ImageView objIMG;
    TextView objTXT;
    //    Vibrator objVIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Illuminance = objSMG.getDefaultSensor(Sensor.TYPE_LIGHT);

        objCAM = android.hardware.Camera.open();

        objTXT = (TextView) findViewById(R.id.textView);
        objIMG = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onResume(){
        super.onResume();
        //Register Listener for changing sensor value
        objSMG.registerListener(this, sensor_Illuminance, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this); // Release sensor Listener
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        objCAM.release();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            int iLux = (int)sensorEvent.values[0];
            objTXT.setText(("Light: " + iLux  + "(lux)"));
            if(iLux <= 80){
                objIMG.setImageResource(R.drawable.lighton);

                objCamPara = objCAM.getParameters();
                objCamPara.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                objCAM.setParameters(objCamPara);
                objCAM.startPreview();

            }else {
                objIMG.setImageResource(R.drawable.lightoff);

                objCamPara = objCAM.getParameters();
                objCamPara.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                objCAM.setParameters(objCamPara);
                objCAM.stopPreview();
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }

}
package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        findViewById(R.id.button).setOnClickListener(this);

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_UI);
        sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            //go back to MainActivity
            finish();
        }
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView tv = findViewById(R.id.textView);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);
        TextView tv4 = findViewById(R.id.textView4);
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            tv4.setText("Prox : " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tv.setText("X : " + sensorEvent.values[0]);
            tv2.setText("Y : " + sensorEvent.values[1]);
            tv3.setText("Z : " + sensorEvent.values[2]);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}

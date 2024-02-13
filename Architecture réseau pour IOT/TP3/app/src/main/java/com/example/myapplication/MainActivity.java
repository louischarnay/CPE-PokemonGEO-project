package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_sensor).setOnClickListener(this);
        findViewById(R.id.button_network).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //go to SensorActivity
        if (view.getId() == R.id.button_sensor) {
            Intent intent = new Intent(this, SensorActivity.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button_network) {
            Intent intent = new Intent(this, NetworkActivity.class);
            startActivity(intent);
        }
    }
}
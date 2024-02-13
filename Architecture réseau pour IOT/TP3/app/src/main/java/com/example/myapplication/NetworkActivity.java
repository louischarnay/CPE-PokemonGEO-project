package com.example.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {
    private BlockingQueue<String> queue;
    private final String IP="192.168.43.157"; // Remplacer par l'IP de votre interlocuteur
    private final int PORT=10000; // Constante arbitraire du sujet
    private InetAddress address; // Structure Java décrivant une adresse résolue
    private DatagramSocket UDPSocket; // Structure Java permettant d'accéder au réseau (UDP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button3) {
            //go back to MainActivity
            finish();
        }
        if (view.getId() == R.id.button2) {
            //TextInputEditText textInputEditText = findViewById(R.id.textInputLayout);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        UDPSocket = new DatagramSocket();
                        address = InetAddress.getByName(IP);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        String message = "(1)";
                        byte[] data = message.getBytes();
                        DatagramPacket packet = new DatagramPacket(data, data.length, address, PORT);
                        UDPSocket.send(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

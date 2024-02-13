package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;

public class MainActivity extends AppCompatActivity {
    private BlockingQueue<String> queue;
    private final String IP="192.168.1.216"; // Remplacer par l'IP de votre interlocuteur
    private final int PORT=10000; // Constante arbitraire du sujet
    private InetAddress address; // Structure Java décrivant une adresse résolue
    private DatagramSocket UDPSocket; // Structure Java permettant d'accéder au réseau (UDP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
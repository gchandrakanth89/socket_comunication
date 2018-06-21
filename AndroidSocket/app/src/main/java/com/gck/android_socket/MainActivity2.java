package com.gck.android_socket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity {


    private static final String TAG = "ServerThread";

    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);


    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(){
            @Override
            public void run() {
                EchoClient client = new EchoClient();
                client.startConnection("localhost", 2222);
                for (int i = 0; i < 100; i++) {
                    client.sendMessage("MSG = "+i);
                }
                client.stopConnection();
            }
        }.start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

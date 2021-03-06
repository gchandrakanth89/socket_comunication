package com.gck.android_socket;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {


    private static final String TAG = "EchoClient";
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
        	e.printStackTrace();
        }

    }

    public String sendMessage(String msg) {
        try {
            Log.d(TAG, "sendMessage: "+msg);
            out.println(msg);
            return in.readLine();
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
		EchoClient client = new EchoClient();
		
		client.startConnection("localhost", 4444);
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 100; i++) {
			
			client.sendMessage("MSG = "+i);
		}
		
		while (true) {
			String msg = scanner.next();
			String sendMessage = client.sendMessage(msg);
			System.out.println(":->"+sendMessage);
			
			if(msg.equals(".")){
				scanner.close();
				break;
			}
		}
		
		
		
		
		client.stopConnection();
	}
}
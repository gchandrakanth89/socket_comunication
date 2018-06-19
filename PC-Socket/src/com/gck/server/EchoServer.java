package com.gck.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public static void main(String[] args) {
		EchoServer server = new EchoServer();
		server.start(4444);
	}

	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Waiting for client");
			clientSocket = serverSocket.accept();
			System.out.println("Client connected");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (".".equals(inputLine)) {
					out.println("good bye");
					break;
				}
				System.out.println(inputLine);
				out.println(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		try {
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

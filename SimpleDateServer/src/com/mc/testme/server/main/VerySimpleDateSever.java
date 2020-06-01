package com.mc.testme.server.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class VerySimpleDateSever {
	public static final int PORT_NUMBER = 54321;
	
	public static void main(String[] args) {
		System.out.println("Start server");
		try (ServerSocket listener = new ServerSocket(PORT_NUMBER)) {
			System.out.println("The date server is running...");
			while (true) {
				try (Socket socket = listener.accept()) {
					System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
					
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println(new Date().toString());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

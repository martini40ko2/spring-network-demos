package com.mc.testme.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public final class VerySimpleDateClient {
	public static final int PORT_NUMBER = 54321;
	public static final String SERVER_HOST = "127.0.0.1";
	
	
	protected static Socket socket;
	protected static Scanner scanner;

	public static void main(String[] args) throws IOException {
//		if (args.length != 1) {
//			System.err.println("Pass the server IP as the sole command line argument");
//			return;
//		}
		socket = new Socket(SERVER_HOST, PORT_NUMBER); //new Socket(args[0], 59090);
		scanner = new Scanner(socket.getInputStream());
		System.out.println("Server response: " + scanner.nextLine());
	}
}

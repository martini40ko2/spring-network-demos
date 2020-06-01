package com.mc.testme.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class VerySimpleChatClient {
	public static final int PORT_NUMBER = 54321;
	public static final String SERVER_HOST = "127.0.0.1";

	public static void main(String args[]) {
		new VerySimpleChatClient(SERVER_HOST, PORT_NUMBER);
	}

	public VerySimpleChatClient(final String host, final int port) {
		try {
			Socket echoSocket = null;
			PrintWriter out = null;
			BufferedReader in = null;

			try {
				echoSocket = new Socket(host, port);
				out = new PrintWriter(echoSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			} catch (IOException e) {
				System.err.println("Unable to get streams from server");
				System.exit(1);
			}

			/** {@link UnknownHost} object used to read from console */
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("client: ");
				String userInput;

				userInput = stdIn.readLine();

				/** Exit on 'q' char sent */
				if ("q".equals(userInput)) {
					break;
				}
				out.println(userInput);
				System.out.println("server: " + in.readLine());
			}
			
			/** Closing all the resources */
			out.close();
			in.close();
			stdIn.close();
			echoSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

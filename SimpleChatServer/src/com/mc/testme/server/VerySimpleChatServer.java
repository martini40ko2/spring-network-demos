package com.mc.testme.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public final class VerySimpleChatServer extends Thread {
	public static final int PORT_NUMBER = 54321;
	protected Socket socket;

	public VerySimpleChatServer(Socket socket) {
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
		start();
	}

	public void run() {
		InputStream in = null;
		OutputStream out = null;

		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String request;
			while ((request = br.readLine()) != null) {
				System.out.println("Message received: " + request);
				request = '[' + new Date().toString() + "] " + request + '\n';
				out.write(request.getBytes());
			}
		} catch (IOException e) {
			System.out.println("Unable to get streams from client");
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT_NUMBER);
			while (true) {
				/**
				 * create a new {@link VerySimpleChatServer} object for each connection this
				 * will allow multiple client connections
				 */
				new VerySimpleChatServer(server.accept());
			}
		} catch (IOException e) {
			System.out.println("Unable to start server.");
		} finally {
			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}

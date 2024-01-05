package fop.w11chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Chat extends MiniJava {
    public static void main(String[] args) {
        while (true) {
            String input = readString(
                    "Enter <port> to start the chat server \n" +
                            "or <host>:<port> to connect to a running server. \n" +
                            "Enter exit to exit the chat.\n");
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting.");
                break;
            }

            if (input.contains(":")) {
                createSocket(input);
            } else {
                createServerSocket(input);
            }
        }
    }

    public static void createServerSocket(String input) {
        int port;
        try {
            port = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Illegal argument");
            return;
        }

        try (ServerSocket server = new ServerSocket(port);
             Socket clientSocket = server.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {
            handleChatSession(sc, reader, writer, "Client");
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void createSocket(String input) {
        String[] arr = input.trim().split(":");
        if (arr.length < 2) {
            System.out.println("Illegal argument");
            return;
        }

        String host = arr[0];
        int port;
        try {
            port = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Illegal argument");
            return;
        }

        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {
            handleChatSession(sc, reader, writer, "Server");
        } catch (ConnectException e) {
            System.out.println("Could not connect to " + host + " on port " + port + ", or it is not free.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void handleChatSession(Scanner sc, BufferedReader reader, PrintWriter writer, String peerName) throws IOException {
        while (true) {
            if (reader.ready()) {
                String response = reader.readLine();
                if (response != null) {
                    System.out.println(peerName + " response: " + response);
                }
            }
            if (sc.hasNextLine()) {
                String message = sc.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    writer.println("The peer has left the chat.");
                    break;
                }
                writer.println(message);
            }
        }
    }
}

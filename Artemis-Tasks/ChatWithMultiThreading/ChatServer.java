package fop.w11pchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static int port = 3000;
    public static synchronized void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler clientHandler : clients) {
            if (!clientHandler.equals(sender)) {
                clientHandler.sendMessage(message);
            }
        }
    }
    public static synchronized  void sendDirectMessage(String recipientName, String message, ClientHandler sender){

        boolean recipientFound = false;
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getClientName().equals(recipientName)) {
                clientHandler.sendMessage(message);
                recipientFound = true;
                break;
            }
        }
        if(!recipientFound){
            sender.sendMessage("Error: No client with the username " + recipientName + " found.");
        }

    }

    public static synchronized void printAllClients(ClientHandler receiver){
        for(ClientHandler clientHandler : clients){
            if(!clientHandler.equals(receiver)){
                receiver.sendMessage(clientHandler.getClientName());
            }
        }
    }
    public static synchronized void SendStarter(String message, ClientHandler sender) {
        sender.sendMessage(message);
    }
    public static void main(String[] args) throws IOException {
        if(args.length == 1){
            port = Integer.parseInt(args[0]);
        }

        ServerSocket serverSocket = new ServerSocket(port);
        while(true){
            Socket clientSocket = serverSocket.accept();
            ClientHandler client = new ClientHandler(clientSocket);
            synchronized (clients){
                clients.add(client);
            }
            new Thread(client).start();
        }
    }
    public static synchronized void removeClientHandler(ClientHandler client){
        clients.remove(client);
    }

}
    

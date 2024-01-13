package fop.w11pchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String clientName;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public ClientHandler(Socket socket){
        this.clientSocket = socket;
        try{
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void sendMessage(String message){
        out.println(message);
    }
    private void handleDirectMessage(String inputLine){
        int firstSpaceIndex = inputLine.indexOf(" ");
        if(firstSpaceIndex != -1){
            String recipientName = inputLine.substring(1, firstSpaceIndex);
            String message = inputLine.substring(firstSpaceIndex + 1);
            ChatServer.sendDirectMessage(recipientName, clientName + " (DM): " + message, this);
        }else{
            sendMessage("Invalid DM format");
        }
    }
    @Override
    public void run() {
        try {

            ChatServer.SendStarter("Hello, welcome to chat. There are several functionality: \n @username<blank>message -- to send in private \n WHOIS -- to receive a list of all currently connected clients \n LOGOUT -- to close connection to the chat \n PINGU -- to send random fact about penguins \n To start, enter your name: ", this);
            clientName = in.readLine();
            ChatServer.broadcastMessage(String.format("[%s] %s: %s", LocalDateTime.now().format(DATE_TIME_FORMATTER), clientName ," joined the chat!"), this);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

                if(inputLine.startsWith("@")){
                    handleDirectMessage(inputLine);
                }else if(inputLine.equals("WHOIS")){
                    ChatServer.printAllClients(this);
                }else if(inputLine.equals("LOGOUT")){
                    cleanup(timestamp);
                }else if(inputLine.equals("PINGU")){
                    ChatServer.broadcastMessage(String.format("[%s] %s: %s", timestamp, "penguins are clever animals!"), this);
                }else{
                    String message = String.format("[%s] %s: %s", timestamp, clientName, inputLine);
                    // Broadcast the message to other clients
                    ChatServer.broadcastMessage(message, this);
                }
            }
        } catch (IOException e) {
            // Handle IOException (e.g., client disconnected)
            System.err.println("IOException in client handler: " + e.getMessage());
        } finally {
            // Clean up (close streams and socket, remove from client list, etc.)
            cleanup(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        }
    }
    private void cleanup(String timestamp) {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) {
                clientSocket.close();
                ChatServer.removeClientHandler(this);
                ChatServer.broadcastMessage(clientName + " left the chat", this);

            }
        } catch (IOException e) {
            // Error handling
        }
    }
}

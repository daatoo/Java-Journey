package fop.w11pchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver implements Runnable{
    private Socket socket;
    private BufferedReader in;


    public Receiver(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }
    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Broadcast the message to other clients
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            // Handle IOException (e.g., client disconnected)
            System.err.println("IOException in Reciever: " + e.getMessage());
        }
    }
}

package fop.w11pchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender implements Runnable{
    private Socket socket;
    private PrintWriter out;
    private Scanner sc = new Scanner(System.in);

    public Sender(Socket socket) throws IOException {
        this.socket= socket;
        out = new PrintWriter(this.socket.getOutputStream(), true);
    }
    @Override
    public void run() {
        while (sc.hasNextLine()) {
            String message = sc.nextLine();
            if ("exit".equalsIgnoreCase(message)) {
                out.println("The peer has left the chat.");
            }
            out.println(message);
        }
    }
}

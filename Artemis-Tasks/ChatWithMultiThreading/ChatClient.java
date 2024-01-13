package fop.w11pchat;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 3000;
        if(args.length == 2){
            port = Integer.parseInt(args[0]);
            host = args[1];
        }

        Socket socket = new Socket(host, port);

        new Thread(new Sender(socket)).start();

        new Thread(new Receiver(socket)).start();
    }
}

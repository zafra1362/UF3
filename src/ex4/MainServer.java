package ex4;

import java.io.IOException;
import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) {
        ElServer server = new ElServer();

        try {
            server.init(7777);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

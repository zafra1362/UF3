package ex4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class ElServer {
    DatagramSocket socket;

    public void init(int port) throws SocketException {
        socket = new DatagramSocket(port);
        System.out.println("Iniciando servidor...");
    }

    public void runServer() throws IOException {
        byte [] receivingData = new byte[4];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;

//el servidor atén el port indefinidament
        while(true){

//creació del paquet per rebre les dades
            DatagramPacket packet = new DatagramPacket(receivingData, 4);
//espera de les dades
            socket.receive(packet);
//processament de les dades rebudes i obtenció de la resposta
            sendingData = processData(packet.getData(), packet.getLength());
//obtenció de l'adreça del client
            clientIP = packet.getAddress();
//obtenció del port del client
            clientPort = packet.getPort();
//creació del paquet per enviar la resposta
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
//enviament de la resposta
            socket.send(packet);

        }
    }

    private byte[] processData(byte[] data, int length) {
        String resp=null;

        return resp.getBytes(StandardCharsets.UTF_8);
    }

}

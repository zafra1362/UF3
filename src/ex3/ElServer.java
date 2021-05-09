package ex3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class ElServer {
    DatagramSocket socket;
    private int elNumero = (int) (Math.random()*100)+1;

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
        String mensaje;
        String resp = null;
        mensaje = new String(data, 0,length);
        int numeroMensaje = 0;

        try{
            numeroMensaje = Integer.parseInt(mensaje);
        }catch (Exception e){
            resp = "eso no es un numero >:(";
             return resp.getBytes(StandardCharsets.UTF_8);
        }
        if(numeroMensaje > elNumero){
            resp = "< el numero es mas pequeño>";
        }else if (numeroMensaje  < elNumero){
            resp = "< el numero es mas GRANDE>";

        }else if (Integer.parseInt(mensaje) == elNumero){
            resp = "< Enhorabuena el numero secreto era: "+elNumero+">";
        } else {
            resp = "adeu";
        }



        System.out.println(mensaje);

        return resp.getBytes(StandardCharsets.UTF_8);
    }

}

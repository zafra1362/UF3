package UDP;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ClienteVelocimetre {
    private int puertodedestino;
    private int resultat;
    private int intentos;
    private String Nombre, ipserver;
    private InetAddress ipDestinatario;
    private MulticastSocket multisocket;
    private InetAddress multicastIP;
    boolean continueRunning = true;
    List<Integer> velocidad = new ArrayList<>();
    InetSocketAddress groupoMulticast;
    NetworkInterface netIf;

    public ClienteVelocimetre(String ip, int puerto) {
        this.puertodedestino = puerto;
        resultat = -3;
        intentos = 0;
        ipserver = ip;
        try {
            multisocket = new MulticastSocket(5557);
            multicastIP = InetAddress.getByName("224.0.0.10");
            groupoMulticast = new InetSocketAddress(multicastIP,5557);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ipDestinatario = InetAddress.getByName(ipserver);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[1024];
        while(true) {
            multisocket.joinGroup(groupoMulticast,netIf);
            while (continueRunning) {
                DatagramPacket mpacket = new DatagramPacket(receivedData, 1024);
                multisocket.receive(mpacket);
                int numero = ByteBuffer.wrap(mpacket.getData()).getInt();
                velocidad.add(numero);
                System.out.println("La velocidad es de: "+numero);
                if (velocidad.size() %5 == 0){
                    int total = 0;
                    for (Integer integer: velocidad){
                        total = total+integer;
                    }
                    int media = total/ velocidad.size();
                    velocidad.clear();
                    System.out.println("La media es de: "+media);
                }
            }
            multisocket.leaveGroup(groupoMulticast,netIf);
            multisocket.close();
        }
    }

    public static void main(String[] args) {
        ClienteVelocimetre clienteAdivina = new ClienteVelocimetre("224.0.0.10", 5556);
        try {
            clienteAdivina.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

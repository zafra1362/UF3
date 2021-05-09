package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class SrvVelocitats {
    int puerto;
    boolean continuar = true;
    MulticastSocket socket;
    InetAddress multicastIP;
    Velocidad simulador;

    public SrvVelocitats(int portValue, String strIp) throws IOException {
        socket = new MulticastSocket(portValue);
        simulador = new Velocidad(100);
        puerto = portValue;
        multicastIP = InetAddress.getByName(strIp);
    }

    public static void main(String[] args) throws IOException {
        SrvVelocitats srvVel = new SrvVelocitats(5557, "224.0.0.10");
        srvVel.runServer();
        System.out.println("Se ha parado");

    }

    public void runServer() throws IOException{
        DatagramPacket packet;
        byte [] sendingData;

        while(continuar){
            sendingData = ByteBuffer.allocate(4).putInt(simulador.agafaVelocitat()).array();
            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, puerto);
            socket.send(packet);

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        socket.close();
    }

}

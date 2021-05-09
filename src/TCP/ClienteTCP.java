package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteTCP extends Thread{

    String hostname;
    int puerto;
    boolean continueConnected;
    int numeroLista;

    public ClienteTCP(String hostname, int puerto) {
        this.hostname = hostname;
        this.puerto = puerto;
        continueConnected = true;
    }



    public Lista getRequest(Lista serverD) {
        if (serverD != null){
            System.out.println(serverD.getNom());
            for(Integer i: serverD.getNumberList()){
                System.out.println(i);
            }
        }


            List<Integer> numeros = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                numeros.add((int) (Math.random()*100+1));
            }
            numeroLista += 1;
            System.out.println("enviada la lista: "+numeroLista);
            return new Lista("listaOrdenada"+numeroLista,numeros);
    }

    private void close(Socket socket){

        try {
            if(socket!=null && !socket.isClosed()){
                if(!socket.isInputShutdown()){
                    socket.shutdownInput();
                }
                if(!socket.isOutputShutdown()){
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        Lista serverData = null;
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;

        try {
            socket = new Socket(InetAddress.getByName(hostname), puerto);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            while(continueConnected){
                Lista lista = getRequest(serverData);
                oos.writeObject(lista);
                oos.flush();
                serverData = (Lista) ois.readObject();
            }

            close(socket);
        } catch (UnknownHostException ex) {
            System.out.println("No se ha establecido la conexión, no existe el host: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error indefinido de conexión: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClienteTCP clientTcp = new ClienteTCP("localhost",5558);
        clientTcp.start();
    }
}
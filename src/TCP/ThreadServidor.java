package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadServidor implements Runnable {

    Socket clientSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Lista listaEntrant;
    boolean acabat;

    public ThreadServidor(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        System.out.println(clientSocket.getInetAddress());
        acabat = false;
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ois = new ObjectInputStream(clientSocket.getInputStream());

    }

    @Override
    public void run() {
        try {
            while(!acabat) {
                listaEntrant = (Lista) ois.readObject();
                System.out.println(listaEntrant.getNom());
                for(Integer i: listaEntrant.getNumberList()){
                    System.out.println(i);
                }
                listaEntrant = generaResposta(listaEntrant);
                oos.writeObject(listaEntrant);
                oos.flush();
            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getLocalizedMessage());
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Lista generaResposta(Lista lista) {
        if (lista != null){
            List<Integer> numerosDesordanados = lista.getNumberList();
            List<Integer> numerosOrdenados = numerosDesordanados.stream().sorted().distinct().collect(Collectors.toList());
            lista.setNumberList(numerosOrdenados);
            return lista;
        }else return null;
    }
}
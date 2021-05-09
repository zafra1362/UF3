package ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        URL web = new URL(sc.nextLine());
        System.out.println("Autoritat:\t"+web.getAuthority());
        System.out.println("Port per defecte:\t"+web.getDefaultPort());
        System.out.println("Recurs:\t"+web.getFile());
        System.out.println("Host:\t"+web.getHost());
        System.out.println("Camí:\t"+web.getPath());
        System.out.println("Protocol:\t"+web.getProtocol());

        //web.openConnection();

        BufferedReader in = new BufferedReader( new InputStreamReader(web.openStream()));

        //File f = new File("blog.xml");
        //BufferedWriter bw;
        //bw = new BufferedWriter(new FileWriter(f));
        String inputLine;
        String etiqueta;
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.contains(sc.nextLine())) {
                System.out.println(inputLine.replaceAll("<\\/?title>","").trim());
                //bw.write(inputLine + "\n");
            }
        }
        //bw.close();
        in.close();
    }
}


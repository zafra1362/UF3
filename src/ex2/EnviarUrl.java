package ex2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class EnviarUrl {
    public static void main(String[] args) throws MalformedURLException {
        try {
            enviarInformacion(new URL("https://docs.google.com/forms/u/0/d/e/1FAIpQLScE6sxLFb3BmCmT2TKHQH5ormS0qvjHwO-uTAR8MXaagBvSSQ/formResponse"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enviarInformacion(URL url) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre:");
        String nom=sc.nextLine();
        System.out.println("Quieres que se publicarlo: (Si/No)");
        String publicable=sc.nextLine();


        URLConnection con = url.openConnection();
        con.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
        out.write("entry.835030737=" + nom+"&"+"entry.1616686619="+publicable);
        out.close();

        con.getInputStream();
    }

}


//https://docs.google.com/forms/u/0/d/e/1FAIpQLScE6sxLFb3BmCmT2TKHQH5ormS0qvjHwO-uTAR8MXaagBvSSQ/formResponse
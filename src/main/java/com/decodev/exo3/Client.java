/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.decodev.exo3;
import java.io.*;
import java.net.*;
/**
 *
 * @author Ce PC
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = null;
        OutputStream sockOut = null;
        InputStream sockIn = null;
        
        try {
            sock = new Socket("localhost", 7777);
            sockOut = sock.getOutputStream();
            sockIn = sock.getInputStream();
            
            // Envoyer la phrase au serveur
            String message = "TLEMCEN GL MASTER";
            sockOut.write(message.getBytes());
            sockOut.flush();
            
            // Lire la réponse du serveur
            byte[] buffer = new byte[1024];
            int lu = sockIn.read(buffer);
            String Response = new String(buffer);
            System.out.println("Réponse du serveur : " + Response);
        }
        catch (UnknownHostException e) {
            System.err.println("Hôte non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Connexion impossible avec : localhost");
            System.exit(1);
        } finally {
            if (sockOut != null)
                sockOut.close();
            if (sockIn != null)
                sockIn.close();
            if (sock != null)
                sock.close();
        }
    }
}

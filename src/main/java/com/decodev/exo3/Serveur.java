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
public class Serveur {
    public static void main(String args[]) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");

                OutputStream sockOut = sock.getOutputStream();
                InputStream sockIn = sock.getInputStream();

                byte[] buffer = new byte[1024];
                int lu = sockIn.read(buffer);
                String message = new String(buffer, 0, lu);
                System.out.println("Message envoyé par le client : " + message);

                // Extraire le dernier caractère de chaque mot et envoyer la réponse au client
                String[] words = message.split(" ");
                String response = "";
                for (String word : words) {
                    response += word.charAt(word.length()- 1);
                }
                sockOut.write(response.getBytes());
                sockOut.flush();

                sockOut.close();
                sockIn.close();
                sock.close();
            }
        } catch (IOException e) {
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

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
public class T extends Thread {
    private Socket socket;

    public T(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter sockOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader sockIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Lire le message envoyé par le client
            String message = sockIn.readLine();
            System.out.println("Message envoyé par le client : " + message);

            // Extraire le dernier caractère de chaque mot et envoyer la réponse au client
            String[] words = message.split(" ");
            StringBuilder responseBuilder = new StringBuilder();
            for (String word : words) {
                responseBuilder.append(word.charAt(word.length() - 1));
            }
            String response = responseBuilder.toString();
            sockOut.println(response);

            // Fermer la connexion avec le client
            sockOut.close();
            sockIn.close();
            socket.close();
        } catch (IOException e) {
        }
    }
}

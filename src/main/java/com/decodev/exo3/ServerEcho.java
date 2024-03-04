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
public class ServerEcho {
    public static void main(String[] args) throws IOException {
        int nbrclient = 0;
        ServerSocket serverSocket = new ServerSocket(7777);
        
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                nbrclient++;
                System.out.println("J'ai " + nbrclient + " clients");

                // Créer un nouveau thread pour gérer la communication avec le client
                Thread T = new T(socket);
                T.start();
            } catch (IOException e) {
            }
        }
    }
}

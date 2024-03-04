/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.decodev.exo2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Ce PC
 */

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("connecte");
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

                // Lire l'entier envoyé par le client
                int entier = dataIn.readInt();

                // Envoyer la suite de Fibonacci correspondante au client
                for (int i = 0; i <= entier; i++) {
                    int fib = fibonacci(i);
                    dataOut.writeInt(fib);
                    dataOut.flush();
                }

                // Fermer les flux et le socket
                dataIn.close();
                dataOut.close();
                socket.close();
            }
        } catch (IOException e) {
        }
    }

    // Méthode pour calculer le nombre de Fibonacci
    private static int fibonacci(int n) {
        if (n <= 1)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

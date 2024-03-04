/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.decodev.exo2;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Ce PC
 */
public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 7777);

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            // Envoyer un entier au serveur
            System.out.print("Entrez un entier: ");
            int entier = scanner.nextInt();
            dataOut.writeInt(entier);
            dataOut.flush();

            // Lire et afficher la table de multiplication de l'entier envoyé par le serveur
            System.out.println("Table des nombres de Fibonacci de " + entier + " sont:");
            for (int i = 0; i <= 10; i++) {
                int result = dataIn.readInt();
                System.out.print(result + " ");
            }
            System.out.println(); // Nouvelle ligne pour une meilleure lisibilité

            // Fermer les flux et le socket
            dataIn.close();
            dataOut.close();
            socket.close();
        } catch (IOException e) {
        }
    }
}

//gerer par le se et la bib java 
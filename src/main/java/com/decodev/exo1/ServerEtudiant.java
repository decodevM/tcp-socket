package com.decodev.exo1;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ce PC
 */
// public class ServerEtudiant {
//     public static void main(String args[]) {
//         Etudiant[] tabEtudiant = {
//             new Etudiant("A", "GL", 13), new Etudiant("B", "GL", 14), new Etudiant("C", "GL", 15),
//             new Etudiant("D", "MID", 16), new Etudiant("E", "MID", 17), new Etudiant("F", "MID", 18),
//             new Etudiant("G", "RSD", 20), new Etudiant("H", "RSD", 11), new Etudiant("I", "RSD", 20),
//             new Etudiant("J", "SIC", 12), new Etudiant("K", "SIC", 18), new Etudiant("L", "SIC", 19)
//         };
//         ServerSocket server = null;
//         try {
//             server = new ServerSocket(7777);
//             while (true) {
//                 Socket sock = server.accept();
//                 System.out.println("connecte");

//                 int receiveBufferSize = sock.getReceiveBufferSize();
//                 int sendBufferSize = sock.getSendBufferSize();
//                 System.out.println("Taille du buffer de réception : " + receiveBufferSize);
//                 System.out.println("Taille du buffer d'envoi : " + sendBufferSize);

//                 System.out.println("Connexion entrante depuis : " + sock.getInetAddress().getHostAddress() + ":" + sock.getPort());
//                 System.out.println("Connexion établie sur le port local : " + sock.getLocalPort());

//                 ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
//                 ObjectInputStream sockIn = new ObjectInputStream(sock.getInputStream());

//                 // Lire l'entier envoyé par le client
//                 int moy = sockIn.readInt();

                // // Filtrer les étudiants ayant une moyenne supérieure à l'entier
                // List<Etudiant> etudSupMoy = new ArrayList<>();
                // for (Etudiant etudiant : tabEtudiant) {
                //     if (etudiant.moy > moy) {
                //         etudSupMoy.add(etudiant);
                //     }
                // }

//                 // Envoyer le tableau d'étudiants au client
//                 sockOut.writeObject(etudSupMoy.toArray(new Etudiant[0]));
//                 sockOut.flush();

//                 String recu;
//                 while ((recu = sockIn.readUTF()) != null) {
//                     System.out.println("recu :" + recu);
//                     String nom = recu.trim();
//                     for (int i = 0; i < tabEtudiant.length; i++) {
//                         if (tabEtudiant[i].getNom().equals(nom)) {
//                             sockOut.writeObject(tabEtudiant[i]);
//                             break;
//                         }
//                     }
//                 }
//                 sockOut.close();
//                 sock.close();
//             }
//         } catch (IOException e) {
//             try {
//                 if (server != null)
//                     server.close();
//             } catch (IOException e2) {
//             }
//         }
//     }
// }

//partie 2
public class ServerEtudiant {
    public static void main(String args[]) {
        Etudiant[] tabEtudiant = {
                new Etudiant("A", "GL", 13), new Etudiant("B", "GL", 14), new Etudiant("C", "GL", 15),
                new Etudiant("D", "MID", 16), new Etudiant("E", "MID", 17), new Etudiant("F", "MID", 18),
                new Etudiant("G", "RSD", 20), new Etudiant("H", "RSD", 21), new Etudiant("I", "RSD", 22),
                new Etudiant("J", "SIC", 23), new Etudiant("K", "SIC", 24), new Etudiant("L", "SIC", 25)
        };
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");

                int receiveBufferSize = sock.getReceiveBufferSize();
                int sendBufferSize = sock.getSendBufferSize();
                System.out.println("Taille du buffer de réception : " + receiveBufferSize);
                System.out.println("Taille du buffer d'envoi : " + sendBufferSize);

                System.out.println("Connexion entrante depuis : " + sock.getInetAddress().getHostAddress() + ":" + sock.getPort());
                System.out.println("Connexion établie sur le port local : " + sock.getLocalPort());

                ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
                ObjectInputStream sockIn = new ObjectInputStream(sock.getInputStream());

                // Lire la spécialité envoyée par le client
                String specialite = sockIn.readUTF();

                // Filtrer les étudiants ayant une moyenne supérieure à l'entier
                Etudiant bestStudent = null;
                int bestMoyenne = Integer.MIN_VALUE;
                for (Etudiant etudiant : tabEtudiant) {
                    if (etudiant.getSpecialite().equals(specialite) && etudiant.moy > bestMoyenne) {
                        bestStudent = etudiant;
                        bestMoyenne = etudiant.moy;
                    }
                }

                // Envoyer l'étudiant au client
                sockOut.writeObject(bestStudent);
                sockOut.flush();

                String recu;
                while ((recu = sockIn.readUTF()) != null) {
                    System.out.println("recu :" + recu);
                    String nom = recu.trim();
                    for (int i = 0; i < tabEtudiant.length; i++) {
                        if (tabEtudiant[i].getNom().equals(nom)) {
                            sockOut.writeObject(tabEtudiant[i]);
                            break;
                        }
                    }
                }
                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                if (server != null)
                    server.close();
            } catch (IOException e2) {
            }
        }
    }
}
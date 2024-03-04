package com.decodev.exo1;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author Ce PC
 */
public class ClientEtudiant {
  public static void main(String[] args) throws IOException {
    String hostName = "localhost";
    //String NomEtudiant = "A";
    Socket sock = null;
    ObjectOutputStream sockOut = null;
    ObjectInputStream sockIn = null;
    Scanner scanner = new Scanner(System.in);
    try {
            sock = new Socket();
            //Q3:Créer un socket et le binder sur le port 8888
            sock.bind(new InetSocketAddress("localhost", 8888));

            // Connexion au serveur
            sock.connect(new InetSocketAddress(hostName, 7777));

      //Q1: Affichage de la taille des zones tampons
            int receiveBufferSize = sock.getReceiveBufferSize();
            int sendBufferSize = sock.getSendBufferSize();
            System.out.println("Taille de la zone tampon de reception : " + receiveBufferSize + " octets");
            System.out.println("Taille de la zone tampon d'envoi : " + sendBufferSize + " octets");

            //Q2: Obtention des numéros de port local et distant
            System.out.println("Numero de port local : " + sock.getLocalPort());
            System.out.println("Numero de port distant : " + sock.getPort());

            sockOut = new ObjectOutputStream(sock.getOutputStream());
            sockIn = new ObjectInputStream(sock.getInputStream());

            // Envoyer la moyenne seuil au serveur
            System.out.print("Entrez un entier entre 1 et 19: ");
            int moy = scanner.nextInt();
            sockOut.writeInt(moy);
            sockOut.flush();

            Object recu = sockIn.readObject(); // récupérer l’objet Etudiant envoyé par le serveur
            if (recu == null)
                System.out.println("erreur de connection");
            else {
                // Lire le tableau d'étudiants envoyé par le serveur
                Etudiant[] etudSupMoy = (Etudiant[]) recu;
                for (Etudiant etudiant : etudSupMoy) {
                    System.out.println("Étudiant avec une moyenne supérieure à " + moy + " : " + etudiant);
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Classe inconnue : " + hostName);
            System.exit(1);
        } catch (SocketException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
      sockOut.close();
        sockIn.close();
        sock.close();
    }
}
//
////partie 2
//public class ClientEtudiant {
//    public static void main(String[] args) throws IOException {
//        String hostName = "localhost";
//        //String NomEtudiant = "A";
//        Socket sock = null;
//        ObjectOutputStream sockOut = null;
//        ObjectInputStream sockIn = null;
//        Scanner scanner = new Scanner(System.in);
//        try {
//            sock = new Socket();
//            //Q3:Créer un socket et le binder sur le port 8888
//            sock.bind(new InetSocketAddress("localhost", 8888));
//
//            // Connexion au serveur
//            sock.connect(new InetSocketAddress(hostName, 7777));
//
//            //Q1: Affichage de la taille des zones tampons
//            int receiveBufferSize = sock.getReceiveBufferSize();
//            int sendBufferSize = sock.getSendBufferSize();
//            System.out.println("Taille de la zone tampon de reception : " + receiveBufferSize + " octets");
//            System.out.println("Taille de la zone tampon d'envoi : " + sendBufferSize + " octets");
//
//            //Q2: Obtention des numéros de port local et distant
//            System.out.println("Numero de port local : " + sock.getLocalPort());
//            System.out.println("Numero de port distant : " + sock.getPort());
//
//            sockOut = new ObjectOutputStream(sock.getOutputStream());
//            sockIn = new ObjectInputStream(sock.getInputStream());
//
//            // Envoyer la spécialité au serveur
//            System.out.print("Entrez une specialité: ");
//            String specialite = scanner.next();
//            sockOut.writeUTF(specialite);
//            sockOut.flush();
//
//            Object recu = sockIn.readObject(); // récupérer l’objet Etudiant envoyé par le serveur
//            if (recu == null)
//                System.out.println("erreur de connection");
//            else {
//                // Recevoir et afficher l'étudiant ayant la meilleure moyenne dans cette spécialité
//                Etudiant etudiant = (Etudiant) recu;
//                System.out.println("Étudiant avec la meilleure moyenne en " + specialite + " : " + etudiant);
//            }
//        } catch (ClassNotFoundException e) {
//            System.err.println("Classe inconnue : " + hostName);
//            System.exit(1);
//        }
//        sockOut.close();
//        sockIn.close();
//        sock.close();
//    }
//}
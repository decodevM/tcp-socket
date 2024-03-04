package com.decodev.additional_part2;

import java.io.*;
import java.net.BindException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7777);
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("Enter patient ID: ");
                Integer id;
                try {
                    id = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid integer.");
                    scanner.next(); // consume invalid input
                    continue; // continue to the next iteration
                }
                if (id == 0) break;
                outputStream.writeInt(id);
                System.out.println("Server -> Client: " + inputStream.readUTF());
            }

        } catch(BindException e){
            System.out.println("Connection refused. Address already in use");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}


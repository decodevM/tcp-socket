package com.decodev.additional_part1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FactorialClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 7777);
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter a number: ");
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                outputStream.writeInt(number);
                outputStream.flush();

                String messageFromServer = inputStream.readUTF();
                System.out.println("Server -> Client: " + messageFromServer);
                
                if (messageFromServer.equals("Close connection")) {
                    break;
                }

                System.out.println("Enter another number: ");
            }

        }catch(BindException e){
                System.out.println("Connection refused. Address already in use");
            }
            catch (IOException e){
                e.printStackTrace();
            }
    }
}

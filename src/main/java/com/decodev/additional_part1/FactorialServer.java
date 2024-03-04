package com.decodev.additional_part1;
import java.io.*;
import java.net.*;
import java.util.HashSet;

public class FactorialServer {
 
    public static void main(String args[]) {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                     DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {

                    System.out.println("Connected");

                    File file = new File("src/main/resources/factorial.txt");
                    HashSet<Integer> resultSet = new HashSet<>();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            resultSet.add(Integer.parseInt(line));
                        }
                    }

                    int number;
                    while (true) {

                        try{
                            number = inputStream.readInt();
                        }catch(EOFException e){
                            System.out.println("Client disconnected");
                            inputStream.read();
                            break;
                        }
                        if(number == 0){
                            break;
                        }

                        System.out.println("Client -> Server: Received number: " + number);
                        int result = factorial(number);
                        if (!resultSet.contains(result)) {
                            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
                                writer.println(result);
                                resultSet.add(result);
                            }
                            outputStream.writeUTF("Factorial of " + number + " is " + result + "\n");
                            outputStream.flush();
                        } else {
                            outputStream.writeUTF("Number already exists, try another one: ");
                        }
                    }

                    outputStream.writeUTF("Close connection");
                }
            }
        } catch(BindException e){
            System.out.println("Connection refused. Address already in use");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static int factorial(int number) {
        if (number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }
}

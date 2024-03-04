# Socket TCP

This repository contains a Java project with multiple examples of TCP socket communication.

## Description
### Example
#### Factorial Calculation Server
The `FactorialServer` class implements a TCP socket server. It accepts connections from clients, receives an integer from the client, calculates the factorial of that number, and sends the result back to the client.

## Usage

To use this project, follow these steps:

1. Clone the repository to your local machine:

```bash
   git clone https://github.com/decodevm/socket-tcp.git
```
2. Navigate to the tcp-socket/src/main/java/com/decodev/additional_part1 directory:

```bash
   cd tcp-socket/src/main/java/com/decodev/additional_part1
```
3. Compile the Java files:

```bash
   javac FactorialServer.java FactorialClient.java
```
4. Run the server for factorial calculation:

```bash
   java FactorialServer
```

5. Connect your client application to the server using TCP socket programming:

```bash
   java FactorialClient
```

## Contributors

This project was developed by:

- [Abdelghani Yacine BARKA](https://github.com/decodevm)
- [Nardjes Sara KHIAT](https://github.com/Nardjes03)

Contributions are welcome! If you'd like to contribute to this project, feel free to fork the repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

package socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOALA
 */
public class SocketServer implements Runnable {
    
    private ServerSocket server;
    private Socket clientSocket;
    private final int port;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    
    public SocketServer() {
        this.server = null;
        this.clientSocket = null;
        this.port = 4602;
        this.outputStream = null;
        this.inputStream = null;
    }
    
    public SocketServer(int port) {
        this.server = null;
        this.clientSocket = null;
        this.port = port;
        this.outputStream = null;
        this.inputStream = null;
    }

    @Override
    public void run() {
        if (openServerSocket())
        {
            if (acceptClientRequest())
            {
                listenClientSocket();
            }
        }
    }
    
    private boolean openServerSocket() {
        boolean open = true;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            open = false;
            System.out.println("I/O error occurred while opening the server socket");
        }
        return open;
    }
    
    private boolean acceptClientRequest() {
        boolean open = true;
        try {
            this.clientSocket = this.server.accept();
            this.outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.inputStream = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException ex) {
            open = false;
            System.out.println("I/O error occurred while accepting client request");
        }
        return open;
    }
    
    private void listenClientSocket() {
        String inLine = null;
        boolean listen = true;
        while (listen) {
            try {
                inLine = inputStream.readLine();
                if (inLine == null || inLine.equals("exit")) {
                    listen = false;
                }
            } catch (IOException ex) {
                listen = false;
                System.out.println("I/O error occurred while reading input stream");
            }
        }
    }
    
}

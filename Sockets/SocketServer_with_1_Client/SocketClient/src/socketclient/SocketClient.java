/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOALA
 */
public class SocketClient implements Runnable {
    private Socket socket;
    private String ipAddress;
    private final int port;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    
    public SocketClient() {
        this.socket = null;
        this.ipAddress = "localhost";
        this.port = 4602;
        this.outputStream = null;
        this.inputStream = null;
    }
    
    public SocketClient(String ipAddress, int port) {
        this.socket = null;
        this.ipAddress = ipAddress;
        this.port = port;
        this.outputStream = null;
        this.inputStream = null; 
    }

    @Override
    public void run() {
        if (openClientSocket()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendRequest();
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("I/O error occurred while closing client socket");
            }
        }
    }
    
    private boolean openClientSocket() {
        boolean open = true;
        try {
            this.socket = new Socket(this.ipAddress, this.port);
        } catch (IOException ex) {
            open = false;
            System.out.println("I/O error occurred while opening the client socket");
        }
        return open;
    }
    
    private void sendRequest() {
        try {
            this.outputStream = new PrintWriter(this.socket.getOutputStream(), true);
            this.inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("I/O error occurred while sending client request");
        }
        this.outputStream.println("exit");
    }
    
}

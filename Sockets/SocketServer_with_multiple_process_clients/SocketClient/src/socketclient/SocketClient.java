package socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
            if (initIOStream()) {
                listenServerSocket();
            }
            closeClientSocket();
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
    
    private boolean initIOStream() {
        boolean init = true;
        try {
            this.outputStream = new PrintWriter(this.socket.getOutputStream(), true);
            this.inputStream = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException ex) {
            init = false;
            System.out.println("I/O error occurred while sending client request");
        }
        return init;
    }
    
    private void listenServerSocket() {
        String inLine = null;
        boolean listen = true;
        while (listen) {
            try {
                inLine = inputStream.readLine();
                if (inLine == null) {
                    listen = false;
                } else {
                    this.outputStream.println(Protocol.processInputStream(inLine));
                    listen = !Protocol.terminateClient();
                }
            } catch (IOException ex) {
                listen = false;
                System.out.println("I/O error occurred while reading input stream");
            }
        }
    }
    
    private void closeClientSocket() {
//        try {
//            socket.close();
//        } catch (IOException ex) {
//            System.out.println("I/O error occurred while closing client socket");
//        }
    }
    
}

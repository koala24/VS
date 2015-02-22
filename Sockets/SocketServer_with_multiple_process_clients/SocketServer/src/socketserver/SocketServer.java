package socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOALA
 */
public class SocketServer implements Runnable {
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final int port;
    private List<IOClientHandler> ioClientHandlers;
    
    public SocketServer() {
        this.serverSocket = null;
        this.clientSocket = null;
        this.port = 4602;
        this.ioClientHandlers = new ArrayList<>();
    }
    
    public SocketServer(int port) {
        this.serverSocket = null;
        this.clientSocket = null;
        this.port = port;
        this.ioClientHandlers = new ArrayList<>();
    }

    @Override
    public void run() {
        if (openServerSocket()) {
            while (!this.serverSocket.isClosed()) {
                if (acceptClientRequest()) {
                    startIOClientHandler();
                }
            }
        }
    }
    
    private boolean openServerSocket() {
        boolean open = true;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            open = false;
            System.out.println("I/O error occurred while opening the server socket");
        }
        return open;
    }
    
    private boolean acceptClientRequest() {
        boolean open = true;
        try {
            this.clientSocket = this.serverSocket.accept();
        } catch (IOException ex) {
            open = false;
            System.out.println("I/O error occurred while accepting client request");
        }
        return open;
    }
    
    private void startIOClientHandler() {
        try {
            IOClientHandler handler = new IOClientHandler(this.clientSocket);
            this.ioClientHandlers.add(handler);
            new Thread(handler).start();
        } catch (IOException ex) {
            System.out.println("I/O error occurred while creating I/O stream");
        }
    }
    
    public void shutdown() {
        for (IOClientHandler handler: this.ioClientHandlers) {
            handler.shutdownClient();
        }
        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}

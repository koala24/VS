package socketserver;

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
public class IOClientHandler implements Runnable {

    private Socket clientSocket;
    private PrintWriter outputStream;
    private BufferedReader inputStream;
    
    private IOClientHandler() {
        
    }
    
    public IOClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outputStream = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.inputStream = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
    }
    
    @Override
    public void run() {
        listenClientSocket();
    }
    
    private void listenClientSocket() {
        String inLine = null;
        boolean listen = true;
        while (listen) {
            try {
                inLine = inputStream.readLine();
                if (inLine == null) {
                    listen = false;
                } else {
                    this.outputStream.println(Protocol.processInputStream(inLine));
                }
            } catch (IOException ex) {
                listen = false;
                System.out.println("I/O error occurred while reading input stream");
            }
        }
    }
    
    public void shutdownClient() {
        try {
            this.clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(IOClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

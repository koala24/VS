package socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KOALA
 */
public class ClientProcess implements Runnable {
    
    public static final String SOCKET_CLIENT_JAR = "C:/MyProjects/VS/Sockets/SocketServer_with_multiple_process_clients/SocketClient/dist/SocketClient.jar";
    public BufferedReader inputStream;
    
    
    public ClientProcess() {
        inputStream = null;
    }

    @Override
    public void run() {
        String inLine = null;
        try {
            Process process = Runtime.getRuntime().exec("java -jar " + SOCKET_CLIENT_JAR);
            try {
                process.waitFor();
                this.inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((inLine = inputStream.readLine()) != null) {
                    System.out.println(inLine);
                }
            } catch (InterruptedException ex) {
                System.out.println("error waitFor client process");
                Logger.getLogger(SocketServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            System.out.println("error exec client process");
            Logger.getLogger(SocketServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

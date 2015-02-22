package socketserver;

/**
 *
 * @author KOALA
 */
public class SocketServerMain {
    
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        SocketServer server = new SocketServer();
        ClientProcessHandler processHandler = new ClientProcessHandler();
        new Thread(server).start();
        processHandler.startClient();
        processHandler.startClient();
        processHandler.startClient();
        Thread.sleep(10000);
        System.out.println("Shutdown server..");
        server.shutdown();
        System.out.println("Server terminated!");
    }
    
}

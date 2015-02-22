package socketserver;

/**
 *
 * @author KOALA
 */
public class SocketServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        SocketServer server = new SocketServer();
        new Thread(server).start();
        Thread.sleep(10000);
        server.shutdown();
    }
    
}

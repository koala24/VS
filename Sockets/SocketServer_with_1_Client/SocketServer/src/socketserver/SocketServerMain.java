package socketserver;

/**
 *
 * @author KOALA
 */
public class SocketServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        new Thread(server).start();
    }
    
}

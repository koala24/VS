package socketclient;

/**
 *
 * @author KOALA
 */
public class SocketClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SocketClient client = new SocketClient();
        new Thread(client).start();
        System.out.println("Client terminated");
    }
    
}

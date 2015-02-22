package socketclient;

/**
 *
 * @author KOALA
 */
public class Protocol {    
    private static boolean terminateClient = false;
    
    public static final String CLIENT_REQ_TERMINATE_SERVER = "EXIT";
    public static final String SERVER_RES_TERMINATE_SERVER = "SERVER_TERMINATED";
    
    public static final String SERVER_REQ_TERMINATE_CLIENTS = "EXIT";
    public static final String CLIENT_RES_TERMINATE_CLIENT = "CLIENT_TERMINATED";
    
    private Protocol() {
        
    }
    
    public static String processInputStream(String inputStream) {
        String response = "";
        if (inputStream.equals(SERVER_REQ_TERMINATE_CLIENTS)) {
            terminateClient = true;
            response = CLIENT_RES_TERMINATE_CLIENT;
        }
        return response;
    }
    
    public static boolean terminateClient() {
        return terminateClient;
    }
}

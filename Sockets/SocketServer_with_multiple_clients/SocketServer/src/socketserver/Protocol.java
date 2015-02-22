/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

/**
 *
 * @author KOALA
 */
public class Protocol {
    
    private static boolean terminateServerByClient = false;
    
    public static final String CLIENT_REQ_TERMINAT_SERVER = "EXIT";
    public static final String SERVER_RES_TERMINAT_SERVER = "SERVER_TERMINATED";
    
    public static final String SERVER_REQ_TERMINAT_CLIENTS = "EXIT";
    public static final String CLIENT_RES_TERMINATE_CLIENT = "CLIENT_TERMINATED";
    
    private Protocol() {
        
    }
    
    public static String processInputStream(String inputStream) {
        String response = "";
        if (inputStream.equals(CLIENT_REQ_TERMINAT_SERVER)) {
            terminateServerByClient = true;
            response = SERVER_RES_TERMINAT_SERVER;
        }
        return response;
    }
    
    public static boolean terminateServer() {
        return terminateServerByClient;
    }
    
}

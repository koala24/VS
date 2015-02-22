package socketserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KOALA
 */
public class ClientProcessHandler {
    
    public List<ClientProcess> clientProcesses;
    
    public ClientProcessHandler() {
        clientProcesses = new ArrayList<>();
    }
    
    /**
     *
     * @param request
     */
    public void startClient() {
        ClientProcess process = new ClientProcess();
        clientProcesses.add(process);
        new Thread(process).start();
    }
    
}

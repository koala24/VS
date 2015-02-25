package rmiserver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author bbhertri
 */
public class RMIServerMain {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        File policy = new File("C:\\Users\\KOALA\\Desktop\\RMI\\security.policy");
        System.setProperty("java.security.policy", policy.getAbsolutePath());
        
if (System.getSecurityManager() == null) {
    System.setSecurityManager(new SecurityManager());
}
       // System.setSecurityManager(new RMISecurityManager());
        // rmiregistry -J-Djava.class.path=C:\Users\bbhertri\Desktop\RMI\Server\src\server -J-Djava.rmi.server.hostname=172.18.22.148
        //Runtime.getRuntime().exec("rmiregistry 2020");
        //LocateRegistry.getRegistry("172.18.22.148", 2020);
        //Registry registry = LocateRegistry.getRegistry(2020);
        LocateRegistry.createRegistry(2020);
        try {
            System.out.println("Registering TimeService");
            TimeServiceImpl tsi = new TimeServiceImpl();
            Naming.rebind("//localhost:2020/TimeService", tsi);
            //Naming.rebind("TimeService", tsi);
            System.out.println(" Done");
        } catch (RemoteException | MalformedURLException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}

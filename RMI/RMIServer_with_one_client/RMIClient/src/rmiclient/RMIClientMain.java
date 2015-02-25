package rmiclient;

import JMatCom.MyTimeStore;
import JMatCom.TimeService;
import JMatCom.TimeStore;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 *
 * @author bbhertri
 */
public class RMIClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String host = "localhost";
        String port = "2020";
        String srv = "TimeService";
        File policy = new File("C:\\Users\\KOALA\\Desktop\\RMI\\security.policy");
        System.setProperty("java.security.policy", policy.getAbsolutePath());
if (System.getSecurityManager() == null) {
    System.setSecurityManager(new SecurityManager());
}
        //System.setSecurityManager(new RMISecurityManager());
        try {
            String url = "rmi://" + host + ":" + port + "/" + srv;
            System.out.println("Looking-up TimeService " + url);
            TimeService ts = (TimeService) Naming.lookup(url);
            System.out.println(" Server time is " + ts.getTime());
            System.out.print(" MyTimeStore contains ");
            TimeStore tsd = new MyTimeStore();
            tsd = ts.storeTime(tsd);
            System.out.println(tsd.getTime());
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}

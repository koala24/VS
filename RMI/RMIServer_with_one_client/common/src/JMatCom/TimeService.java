package JMatCom;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author bbhertri
 */
public interface TimeService extends Remote {

    public String getTime() throws RemoteException;

    public TimeStore storeTime(TimeStore store) throws RemoteException;
}

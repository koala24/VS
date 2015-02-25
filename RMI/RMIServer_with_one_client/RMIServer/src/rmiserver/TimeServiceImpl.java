package rmiserver;

import JMatCom.TimeService;
import JMatCom.TimeStore;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author bbhertri
 */
public class TimeServiceImpl extends UnicastRemoteObject implements TimeService {

    public TimeServiceImpl() throws RemoteException {
    }

    @Override
    public String getTime() throws RemoteException {
        GregorianCalendar cal = new GregorianCalendar();
        StringBuilder sb = new StringBuilder();
        sb.append(cal.get(Calendar.HOUR_OF_DAY));
        sb.append(":" + cal.get(Calendar.MINUTE));
        sb.append(":" + cal.get(Calendar.SECOND));
        return sb.toString();
    }

    @Override
    public TimeStore storeTime(TimeStore store) throws RemoteException {
        store.setTime(getTime());
        return store;
    }
}

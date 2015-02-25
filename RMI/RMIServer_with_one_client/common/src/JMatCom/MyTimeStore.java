package JMatCom;

import JMatCom.TimeStore;
import java.io.Serializable;

/**
 *
 * @author bbhertri
 */
public class MyTimeStore implements Serializable, TimeStore {

    private String time;

    public MyTimeStore() {
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }
}

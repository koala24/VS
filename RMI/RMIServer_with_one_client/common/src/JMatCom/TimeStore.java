package JMatCom;

import java.io.Serializable;

/**
 *
 * @author bbhertri
 */
public interface TimeStore extends Serializable {

    public void setTime(String time);

    public String getTime();
}

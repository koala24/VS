/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage;

/**
 *
 * @author KOALA
 */
public class Car implements Comparable<Object> {
    /**
     * Das Autokennzeichen.
     */
    private String flag;
    /**
     * Der Zeitpunkt, an dem das Auto parken möchte.
     */
    private int parkDate;
    /**
     * Die Parkdauer.
     */
    private int parkDuration;

    public Car()
    {
        setFlag("");
        setParkDate(0);
        setParkDuration(0);
    }

    public Car(String flag, int parkDate, int parkDuration)
    {
        setFlag(flag);
        setParkDate(parkDate);
        setParkDuration(parkDuration);
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public int getParkDate()
    {
        return parkDate;
    }

    public void setParkDate(int parkDate)
    {
        this.parkDate = parkDate;
    }

    public int getParkDuration()
    {
        return parkDuration;
    }

    public void setParkDuration(int parkDuration)
    {
        this.parkDuration = parkDuration;
    }
    /** Zur Erinnerung:
     *  1: Object ist übergeordnet
     *  0: Object ist identisch
     * -1: Object ist untergeordnet
     */
    public int compareTo(Object car)
    {
        if (this.parkDate > ((Car) car).getParkDate())
        {
            return 1;
        }
        else if (this.parkDate < ((Car) car).getParkDate())
        {
            return -1;
        }
        return 0;
    }
}

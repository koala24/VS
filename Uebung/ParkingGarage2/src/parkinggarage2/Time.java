/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage2;

/**
 *
 * @author KOALA
 */
public class Time {
    private int hours;
    private int minutes;

    public Time()
    {
        setHours(0);
        setMinutes(0);
    }

    public Time(int hours, int minutes)
    {
        setHours(hours);
        setMinutes(minutes);
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public long toMillis()
    {
        //return hours * 3600000 + minutes * 60000;
        return hours * 1000 + minutes % 60 * 17;
    }

    public String toString()
    {
        return "Stunden: " + hours + " Minuten: " + minutes;
    } 
}

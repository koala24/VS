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
public class Car implements Runnable {
    /**
     * Das Autokennzeichen.
     */
    private String flag;
    /**
     * Der Zeitpunkt, an dem das Auto parken möchte.
     */
    private Time parkDate;
    /**
     * Die Parkdauer.
     */
    private Time parkDuration;
    /**
     * Zeit für die periodische Abfrage, ob Platz im Parkhaus ist.
     */
    private Time parkNewRequest;
    /**
     * 
     */
    Thread thread;

    public Car()
    {
        setFlag("");
        setParkDate(new Time());
        setParkDuration(new Time());
        parkNewRequest = new Time(0, 6);
    }

    public Car(String flag, Time parkDate, Time parkDuration)
    {
        setFlag(flag);
        setParkDate(parkDate);
        setParkDuration(parkDuration);
        parkNewRequest = new Time(0, 6);
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public Time getParkDate()
    {
        return parkDate;
    }

    public void setParkDate(Time parkDate)
    {
        this.parkDate = parkDate;
    }

    public Time getParkDuration()
    {
        return parkDuration;
    }

    public void setParkDuration(Time parkDuration)
    {
        this.parkDuration = parkDuration;
    }

    public void run()
    {
        boolean foundParkPlace = false;
        try
        {
            Thread.sleep(parkDate.toMillis());  // Zeit bis zur Ankuft am Parkhaus
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (ParkingGarage.getInstance().isOpen())  // Hat das Parkhaus noch offen?
        {
            foundParkPlace = ParkingGarage.getInstance().addParkedCar(this);
            if (!foundParkPlace)  // Hat das Parkhaus noch einen Platz frei?
            {
                ParkingGarage.getInstance().addCarToQueue(this);  // Das Auto in die Warteschlange einreihen.
                ParkingGarage.getInstance().printCars();
                do
                {
                    try
                    {
                        Thread.sleep(parkNewRequest.toMillis());  // Periodische Wartezeit bis ein Platz im Parkhaus frei geworden ist.
                    } catch (InterruptedException e) {
                    }
                    if (foundParkPlace = ParkingGarage.getInstance().addParkedCar(this))  // Gibt es jetzt einen freien Parkplatz?
                    {
                        ParkingGarage.getInstance().removeCarFromQueue(this);  // Wenn ja, raus aus der Warteschlange!
                    }
                } while (ParkingGarage.getInstance().isOpen() && !foundParkPlace);
            }
            if (foundParkPlace)
            {
                ParkingGarage.getInstance().printCars();
                try
                {
                    Thread.sleep(parkDuration.toMillis());  // Die Parkzeit läuft.
                } catch (InterruptedException e) {
                }
                ParkingGarage.getInstance().removeParkedCar(this);  // Der Parkplatz wird freigegeben.
                ParkingGarage.getInstance().printCars();
            }
        }
    }

    public void start()
    {
        if (thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }
    /**
     * http://www.dpunkt.de/java/Programmieren_mit_Java/Multithreading/6.html
     * http://www.dpunkt.de/java/Programmieren_mit_Java/Grafikprogrammierung/7.html
     */
    public void stop()
    {
        thread = null;
    }
}

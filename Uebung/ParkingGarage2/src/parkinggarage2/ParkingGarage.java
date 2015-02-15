/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Singleton (Thread-safe):
 * http://www.theserverside.de/singleton-pattern-in-java/
 * 
 * @author KOALA
 */
public class ParkingGarage {
    private static ParkingGarage instance = new ParkingGarage();

    /**
     * Die Anzahl der verfügbaren Parkplätze.
     */
    private int numParkingPlaces;
    /**
     * Die geparkten Autos.
     */
    private ArrayList<Car> parkedCars;
    /**
     * Die Warteschlange vor der Eingangsschranke.
     */
    private ArrayList<Car> queue;

    private ParkingGarage()
    {
        setNumParkingPlaces(0);
        parkedCars = new ArrayList<Car>();
        queue = new ArrayList<Car>();
    }

    public static ParkingGarage getInstance()
    {
        return instance;
    }

    public int getNumParkingPlaces()
    {
        return numParkingPlaces;
    }

    public void setNumParkingPlaces(int numParkingPlaces)
    {
        this.numParkingPlaces = numParkingPlaces;
    }

    public synchronized boolean addParkedCar(Car car)
    {
        return this.parkedCars.size() < numParkingPlaces ? parkedCars.add(car) : false;
    }

    public synchronized void removeParkedCar(Car car)
    {
        parkedCars.remove(car);
    }

    public synchronized void addCarToQueue(Car car)
    {
        queue.add(car);
    }

    public synchronized void removeCarFromQueue(Car car)
    {
        queue.remove(car);
    }

    public synchronized void printParkedCars()
    {
        Car car = null;
        if (parkedCars.size() > 0)
        {
            for (Iterator<Car> it = parkedCars.iterator(); it.hasNext();)
            {
                car = it.next();
                System.out.println("Flag: " + car.getFlag() + " Park Date: "
                                + car.getParkDate() + " Park Duration: "
                                + car.getParkDuration().toString());
            }
        }
        else
        {
            System.out.println("[Alle Parkplätze sind frei!]");
        }
    }

    public synchronized void printQueuedCars()
    {
        Car car = null;
        if (queue.size() > 0)
        {
            for (Iterator<Car> it = queue.iterator(); it.hasNext();)
            {
                car = it.next();
                System.out.println("Flag: " + car.getFlag() + " Park Date: "
                                + car.getParkDate() + " Park Duration: "
                                + car.getParkDuration().toString());
            }
        }
        else
        {
            System.out.println("[Warteschlange ist leer!]");
        }
    }

    public synchronized void printCars()
    {
        System.out.print("\n################################################################################\n\n");
        System.out.println(" - Geparkte Autos - \n");
        printParkedCars();
        System.out.println();
        System.out.println(" - Warteschlange - \n");
        printQueuedCars();
    }

    public boolean isOpen()
    {
        return true;
    }
}

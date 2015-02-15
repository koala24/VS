/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author KOALA
 */
public class ParkingGarage {
    /**
     * Die Anzahl der verfügbaren Parkplätze.
     */
    private int numParkingPlaces;
    /**
     * Die geparkten Autos.
     */
    private ArrayList<Car> parkedCars;

    public ParkingGarage()
    {
        setNumParkingPlaces(0);
        parkedCars = new ArrayList<Car>();
    }

    public ParkingGarage(int numParkingPlaces)
    {
        setNumParkingPlaces(numParkingPlaces);
        parkedCars = new ArrayList<Car>();
    }

    public int getNumParkingPlaces()
    {
        return numParkingPlaces;
    }

    public void setNumParkingPlaces(int numParkingPlaces)
    {
        this.numParkingPlaces = numParkingPlaces;
    }

    public boolean addParkedCar(Car parkedCars)
    {
        boolean enoughParkingPlaces = true;
        if (this.parkedCars.size() < numParkingPlaces)
        {
            this.parkedCars.add(parkedCars);
        }
        else
        {
            enoughParkingPlaces = false;
        }
        return enoughParkingPlaces;
    }

    public void printCars()
    {
        for (Car car : parkedCars)
        {
            System.out.println("Flag: " + car.getFlag() + " Park Date: "
                            + car.getParkDate() + " Park Duration: "
                            + car.getParkDuration());
        }
    }

    public void updateParkingTimes(int hours)
    {
        int duration = 0;
        Car car = null;
        for (Iterator<Car> it = parkedCars.iterator(); it.hasNext();)
        {
            car = it.next();
            duration = car.getParkDuration() - hours;
            if (duration == 0)
            {
                it.remove();
            }
            else
            {
                car.setParkDuration(duration);
            }
        }
    }  
}

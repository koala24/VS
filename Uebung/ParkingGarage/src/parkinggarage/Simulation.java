/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author KOALA
 */
public class Simulation {
    /**
     * Die Autos, die gerne parken möchten.
     */
    private ArrayList<Car> cars;
    /**
     * Das Parkhaus.
     */
    ParkingGarage parkingGarage;

    public Simulation()
    {
        cars = new ArrayList<>();
        parkingGarage = new ParkingGarage(4);
    }

    public void readFileCSV(String file) throws IOException
    {
        String[] data = null;
        Car car = new Car();
        boolean ok = true;
        try
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader csvFile = new BufferedReader(fileReader);
            String dataRow = csvFile.readLine();
            while (dataRow != null)
            {
                data = dataRow.split(";");
                car.setFlag(data[0]);
                car.setParkDate(Integer.parseInt(data[1]));
                car.setParkDuration(Integer.parseInt(data[2]));
                cars.add(car);
                dataRow = csvFile.readLine();
                car = new Car();
            }
            csvFile.close();
        }
        catch (IOException ex)
        {
            throw ex;
        }
    }

    public void simulate()
    {
        int hour = 0;
        Collections.sort(cars);
        while (hour < 24)
        {
            parkingGarage.updateParkingTimes(1);
            while (cars.size() > 0 && parkingGarage.addParkedCar(cars.get(0)))
            {
                cars.remove(0);
            }
            printCurrentResults();
            hour += 1;
        }
    }

    public void printCars()
    {
        for (Car car : cars)
        {
            System.out.println("Flag: " + car.getFlag() + " | Park Date: "
                               + car.getParkDate() + " | Park Duration: "
                               + car.getParkDuration());
        }
    }

    public void printCurrentResults()
    {
        System.out.println("############### Zwischenstand ###############\n");
        System.out.println("- Autos die sich im Parkhaus befinden -\n");
        parkingGarage.printCars();
        System.out.println("\n- Autos die sich in der Warteschlange befinden -\n");
        printCars();
    }

    public void printSimulationResults()
    {
        System.out.println("############### Ergebnis ###############\n");
        System.out.println("- Autos die sich noch nach der Öffnungszeit im Parkhaus befanden -\n");
        parkingGarage.printCars();
        System.out.println("\n- Autos die sich noch in der Warteschlange befanden -\n");
        printCars();
    }
}

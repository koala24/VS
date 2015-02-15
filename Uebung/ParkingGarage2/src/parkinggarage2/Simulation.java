/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public Simulation()
    {
        cars = new ArrayList<Car>();
        ParkingGarage.getInstance().setNumParkingPlaces(4);
    }

    public Simulation(int numParkingPlaces)
    {
        cars = new ArrayList<Car>();
        ParkingGarage.getInstance().setNumParkingPlaces(numParkingPlaces);
    }

    public void readFileCSV(String file) throws IOException
    {
        try
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader csvFile = new BufferedReader(fileReader);
            String dataRow = csvFile.readLine();
            String[] data = null;
            String[] time = null;
            Car car = new Car();
            while (dataRow != null)
            {
                data = dataRow.split(";");
                car.setFlag(data[0]);
                time = data[1].split(":");
                car.setParkDate(new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
                time = data[2].split(":");
                car.setParkDuration(new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1])));
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
        for (Car car : cars)
        {
            car.start();
        }
    }
}

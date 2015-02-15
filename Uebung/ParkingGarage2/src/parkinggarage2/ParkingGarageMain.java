/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinggarage2;

import java.io.IOException;

/**
 *
 * @author KOALA
 */
public class ParkingGarageMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        try {
            simulation.readFileCSV("config.csv");
            simulation.simulate();
        } catch (IOException ex) {
            System.out.println("Die CSV-Datei konnte nicht gelesen werden.");
        }
    }
    
}

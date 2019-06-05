package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFiles {

    LocationsContainer locations = new LocationsContainer();

    public ReadFiles(){}

    public void readcoordinates(){
        try {
            Scanner scanner = new Scanner(new File("geocodes.csv"));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                Location location = locations.findById(Integer.parseInt( values[1]));

                if(location==null)
                    locations.addLocation(new Location(Integer.parseInt( values[1]),"",Double.parseDouble(values[2]),Double.parseDouble(values[3]),0));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readNames(){
        try {
            Scanner scanner = new Scanner(new File("breweries.csv"));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                if(values.length<2)
                    continue;

                Location location;

                try {
                    location = locations.findById(Integer.parseInt(values[0]));
                }catch (NumberFormatException e){
                    continue;
                }

                if(location!=null)
                   location.setName(values[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void readBears(){
        try {
            Scanner scanner = new Scanner(new File("beers.csv"));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");

                if(values.length<2)
                    continue;

                Location location;
                try {
                     location = locations.findById(Integer.parseInt(values[1]));
                }catch (NumberFormatException e){
                    continue;
                }


                if(location!=null) {
                    location.addBeerCount();
                    location.AddBeerType(values[2]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LocationsContainer getLocations(){
        return locations;
    }
}

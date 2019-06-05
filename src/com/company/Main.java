package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Write latitude of your location (e.g. 51.1102662)");
        String latitude = in.nextLine();

        System.out.println("Write longitude of your location (e.g. 10.04521)");
        String longitude = in.nextLine();

        double latitudeNumber;
        double longitudeNumber;
        try{
           latitudeNumber =  Double.parseDouble(latitude);
           longitudeNumber = Double.parseDouble(longitude);
        }
        catch (NumberFormatException e){
            System.out.println("entered location not exist or check format. Please rerun program");
            return;
        }

        long startTime = System.currentTimeMillis();

        Calculate cal = new Calculate();
        cal.findBestPath(latitudeNumber, longitudeNumber);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Program took: "+ totalTime+" ms");

    }



}

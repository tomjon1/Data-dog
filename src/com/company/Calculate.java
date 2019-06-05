package com.company;

import java.util.ArrayList;


public class Calculate {

    LocationsContainer lc;
    ReadFiles rd;

    public Calculate() {
        rd = new ReadFiles();

        rd.readcoordinates();
        rd.readBears();
        rd.readNames();

        lc = rd.getLocations();
    }

    //create matrix to same distances between locations
    private double[][] calculateDistances(){
        double[][] distances = new double[lc.size()][lc.size()]; //create matrix to same distances between locations

        //to calculate all distances
        for (int i =0; i<lc.size();i++){
            for (int j =0; j<lc.size();j++) {
                if(i!=j){
                    distances[i][j] = distance(lc.getLocation().get(i).getLatitude(),lc.getLocation().get(i).getLongitude(),
                            lc.getLocation().get(j).getLatitude(),lc.getLocation().get(j).getLongitude() );
                }
            }
        }
        return  distances;
    }


    public void findBestPath(double latitude,double longitude ){
        //add home location
        lc.addStartLocation(new Location(0,"Home", latitude, longitude,0));

        // save created distance matrix
        double[][] distances = calculateDistances();

        double totalDistance =0; //total distance
        int index =0; //index of list where are saved information about locations
        int beerCount =0; //to count total beer count

        ArrayList<Integer> nodes = new ArrayList<>();
        ArrayList<Double> distanceBetweenLocations = new ArrayList<>();

        //search for next node until distace is 2000
        while((totalDistance+ distances[index][0])<=2000){
            double nearestBrewery = Double.MAX_VALUE;
            double beerCof = Double.MAX_VALUE;
            int indextemp =0; // to save index for shortest distance in current location
            int beers=0; //to count how many beers are in next selected location

            nodes.add(index); // to add all indexes of breweries

            for (int i =1 ; i <distances[0].length; i++){
                int countofBeers = lc.getLocation().get(i).getBeerCount();
                double tempDistance = distances[index][i];

                if ((!nodes.contains(i)  && (totalDistance+ distances[index][i]+ distances[i][0])<=2000 )) {
                    if( beerCof > tempDistance/countofBeers ){
                        beerCof = tempDistance/countofBeers;
                        indextemp =i;
                        nearestBrewery = tempDistance;
                        beers =countofBeers;
                    }
                }
            }

            //if dont found brewery from which helicopter still would have enough fuel to come home
            if(indextemp==0)
                break;
            //to save information about next location
            index = indextemp;
            totalDistance += nearestBrewery;
            distanceBetweenLocations.add(nearestBrewery);
            beerCount +=beers;
        }

        //to add Home location
        distanceBetweenLocations.add(0,0.0);
        nodes.add(0);
        distanceBetweenLocations.add(distances[0][index]);

        printData(nodes, distanceBetweenLocations, beerCount, totalDistance);

    }


    //print results
    private void printData(ArrayList<Integer> nodes,  ArrayList<Double> distanceBetweenLocations, int beerCount, double totalDistance ){
        //print locations
        System.out.println("Found "+ (nodes.size()-2)+" beer factories");
        for (int i =0 ; i<nodes.size();i++){
            System.out.printf("-> %s: %f, %f distance: %.3f KM %n ", lc.getLocation().get(nodes.get(i)).getName(), lc.getLocation().get(nodes.get(i)).getLatitude() ,lc.getLocation().get(nodes.get(i)).getLongitude(), distanceBetweenLocations.get(i));
        }
        System.out.println();
        System.out.printf("Total distance traveled: %.2f KM %n", totalDistance);


        //print beers
        System.out.println();
        System.out.println("Found "+beerCount+" beer factories");
        for (int i =0 ; i<nodes.size();i++){
            for (int j = 0; j<lc.getLocation().get(nodes.get(i)).getBeerCount(); j++)
                System.out.println("-> "+ lc.getLocation().get(nodes.get(i)).getBeerTypes().get(j) );
        }

    }

    //calculate distace between geo coordinates
    private  double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;

            return (dist);
        }
    }

}

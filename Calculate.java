package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Calculate {

    LocationsContainer lc;
    ReadFiles rd;

    public Calculate() {
        rd = new ReadFiles();
    }


    public void doActions(){
        rd.readcoordinates();
        rd.readBears();
        rd.readNames();

        lc = rd.getLc();

        //change that longt and lat would be from console
        lc.addStartLocation(new Location(0,"Home",51.355468,11.100790,0));

        double[][] distances = new double[lc.size()][lc.size()];

        //to calculate all distaces
        for (int i =0; i<lc.size();i++){
            for (int j =0; j<lc.size();j++) {
                if(i!=j){
                    distances[i][j] = distance(lc.Locations.get(i).getLat(),lc.Locations.get(i).getLongt(), lc.Locations.get(j).getLat(),lc.Locations.get(j).getLongt(), "K" );
                }

            }
        }

        double distanceKM =0;
        int index =0;
        ArrayList<Integer> nodes = new ArrayList<>();
        ArrayList<Double> disc = new ArrayList<>();
        while((distanceKM+ distances[index][0])<2000){
            double shortest = 20000;
            nodes.add(index);
            int indextemp =0;
            for (int i =1 ; i <distances[0].length; i++){

                double ttt = distanceKM+ distances[index][i]+ distances[i][0];
                if ((!nodes.contains(i) && distances[index][i]<shortest && (distanceKM+ distances[index][i]+ distances[i][0])<2000 )){
                    indextemp =i;
                    shortest = distances[index][i];
                }
            }
            if(indextemp==0)
                break;
            index = indextemp;
            distanceKM += shortest;
            disc.add(shortest);
        }
        disc.add(distances[0][index]);
        double test =0;
        for(double a :disc){
            test+=a;
        }
    }

    private  double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit == "K") {
                dist = dist * 1.609344;
            } else if (unit == "N") {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }



}

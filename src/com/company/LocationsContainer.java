package com.company;

import java.util.ArrayList;

public class LocationsContainer {

   private ArrayList<Location> location = new ArrayList<>();

    public LocationsContainer (){}

    public Location findById(int id){
        for (Location loc: location) {
            if( loc.getId()==id)
                return loc;
        }

        return null;
    }

    public void addLocation(Location loc){
        location.add(loc);
    }

    public void addStartLocation(Location loc){
        location.add(0,loc);
    }

    public int size(){
        return  location.size();
    }

    public ArrayList<Location> getLocation() {
        return location;
    }
}

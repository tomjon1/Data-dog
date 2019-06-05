package com.company;

import java.util.ArrayList;

public class Location {
    private int id;
    private String name;
    private double longitude;
    private double latitude;
    private int beerCount;
    private ArrayList<String> beerTypes =new ArrayList<>();


    public Location(int id, String name, double latitude, double longitude, int beerCount) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.beerCount = beerCount;
    }


    public ArrayList<String>getBeerTypes(){
        return beerTypes;
    }

    public void AddBeerType(String type) {
        beerTypes.add(type);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getBeerCount() {
        return beerCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void addBeerCount() {
        beerCount += 1;
    }


    public void setName(String name){
        this.name = name;
    }
}
